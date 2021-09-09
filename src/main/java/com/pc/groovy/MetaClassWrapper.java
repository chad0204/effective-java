package com.pc.groovy;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.Tuple;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.PogoMetaClassSite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * groovy的方法查找策略，默认先从this.getClass的metaClass里找，再从上下文里找，这里修改策略，反过来找<br /> 当把一个java
 * method放入groovy后，会自动被包装为org.codehaus.groovy.runtime.MethodClosure，如果本身就是java类，则无包装<br />
 * 替换metaClass见这里：http://www.jroller.com/melix/entry/using_custom_groovy_metaclasses_to，具体代码见：groovy.lang.
 * MetaClassRegistry<br /> 可查看groovy的metaClass的创建过程，几个类Script,GroovyObjectSupport,InvokerHelper.getMetaClass,GroovySystem.getMetaClassRegistry,
 * MetaClassRegistryImpl
 */
public class MetaClassWrapper extends groovy.lang.MetaClassImpl {

    private groovy.lang.Script script;

    public MetaClassWrapper(final groovy.lang.Script script) {
        super(script.getClass());

        this.script = script;
        this.initialize();// 十分重要，解析该class的方法和属性，免得报下面的异常
        // initialize must be called for meta class of class Clz_s1(class tools.utils.script.MetaClassWrapper) to
        // complete initialisation process before any invocation or field/property access can be done
    }

    public Object tryGetFromBinding(String methodName) {
        Binding binding = script.getBinding();

        // 优先从Binding里查找
        if (binding.hasVariable(methodName)) {
            return binding.getVariable(methodName);
        }

        return null;
    }

    @Override
    public Object invokeMethod(Object object, String methodName, Object[] originalArguments) {
        // println("invokeMethod_args:" + methodName + ",args:" + originalArguments + ",object:" + object.getClass());

        // 记得要转换为object，免得递归调用了
        return this.invokeMethod(object, methodName, (Object) originalArguments);
    }

    // @Override
    public Object invokeMethod_(Object object, String methodName, Object arguments) {
        // 全局的闭包、往上下文里放的java method等的调用会走到这里
        // class里的闭包的方法，不会走到这里，会生成一个新的class，metaclass为org.codehaus.groovy.runtime.metaclass.ClosureMetaClass
        // 【重要】只有在调用那些class里面没有定义的方法才会走到这里，class里定义的闭包、def等，都直接生成了class的method，不会走到这里

        if (object == null) {
            throw new NullPointerException("Cannot invoke method: " + methodName + " on null object");
        }

        // 优先看有没有在上下文
        Object v = tryGetFromBinding(methodName);
        if (v != null) {
            if (v instanceof Closure) {
                Closure<?> closure = ((Closure<?>) v);
                return closure.getMetaClass().invokeMethod(closure, "doCall", (Object[]) arguments);
            }

            return v;
        }

        Object[] args = null;

        // 调整参数
        if (arguments == null) {
            args = MetaClassHelper.EMPTY_ARRAY;
        }
        if (arguments instanceof Tuple) {
            Tuple tuple = (Tuple) arguments;
            args = tuple.toArray();
        }
        if (arguments instanceof Object[]) {
            args = (Object[]) arguments;
        } else {
            args = new Object[]{arguments};
        }

        // 那些真正找不到的、未定义的方法才能走到这里，class自定义的method不会走到这里
        return this.invokeMethod(theClass, object, methodName, args, false, false);
    }

    // @Deprecated
    public Object invokeMethod(Object object, String methodName, Object arguments) {
        // 全局的闭包、往上下文里放的java method等的调用会走到这里
        // class里的闭包的方法，不会走到这里，会生成一个新的class，metaclass为org.codehaus.groovy.runtime.metaclass.ClosureMetaClass
        // 【重要】只有在调用那些class里面没有定义的方法才会走到这里，class里定义的闭包、def等，都直接生成了class的method，不会走到这里

        if (object == null) {
            throw new NullPointerException("Cannot invoke method: " + methodName + " on null object");
        }

        Object[] args = null;

        // 调整参数
        if (arguments == null) {
            args = MetaClassHelper.EMPTY_ARRAY;
        }
        if (arguments instanceof Tuple) {
            Tuple tuple = (Tuple) arguments;
            args = tuple.toArray();
        }
        if (arguments instanceof Object[]) {
            args = (Object[]) arguments;
        } else {
            args = new Object[]{arguments};
        }

        // 优先看有没有在上下文
        Object v = tryGetFromBinding(methodName);
        if (v != null) {
            if (v instanceof Closure) {
                Closure<?> closure = ((Closure<?>) v);

                // 方式1
                return closure.getMetaClass().invokeMethod(closure, "doCall", args);

            } else if (v instanceof Method) {// 如果是方法，直接反射之
                Method m = (Method) v;
                try {
                    // 只为性能考虑
                    return m.invoke(object, args);// 只能解决基本类型的参数，且不解决【String... args】
                } catch (Throwable e) {
                    if (e instanceof InvocationTargetException) {
                        InvocationTargetException invocationEx = (InvocationTargetException) e;
                        Throwable targetEx = invocationEx.getTargetException();// 直接抛出目标异常
                        if (targetEx instanceof RuntimeException) {
                            throw (RuntimeException) targetEx;
                        } else {
                            throw new RuntimeException(targetEx);
                        }
                    }
                }
            }

            return v;
        }

        // 那些真正找不到的、未定义的方法才能走到这里，class自定义的method不会走到这里 2013-05-23
        return this.invokeMethod(theClass, object, methodName, args, false, false);
    }

    /**
     * 脚本、类里自定义的method，会走到这里，闭包什么的不会走到这里
     */
    @Override
    protected Object chooseMethod(String methodName, Object methodOrList,
                                  @SuppressWarnings("rawtypes") Class[] arguments) {
        return super.chooseMethod(methodName, methodOrList, arguments);
    }

    /**
     * 方法和闭包都会走到这里，且只走一次
     */
    @SuppressWarnings("rawtypes")
    @Override
    public CallSite createPogoCallCurrentSite(CallSite site, Class sender, Object[] args) {
        String methodName = site.getName();
        Object v = tryGetFromBinding(methodName);
        if (v != null) {
            if (v instanceof Closure) {
                return new PogoMetaClassSite(site, this);
            }
        }

        // 返回
        return super.createPogoCallCurrentSite(site, sender, args);
    }

}

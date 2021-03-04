package com.pc.annotation.spring;

/**
 *
 * 循环下面两步，可以循环获取注解中的注解
 * class.getAnnotation(xx.class)
 *
 * annotation.annotationType()
 *
 *
 * @author pengchao
 * @date 08:23 2020-05-28
 */
@Profile("other")
public class UseProfile {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        if(UseProfile.class.getAnnotation(Profile.class)!=null) {
            //获取profile注解值
            String[] values = UseProfile.class.getAnnotation(Profile.class).value();

            //获取profile注解的注解Conditional的value，也就是获取用于匹配的类
            Class<? extends Condition>[] coditions = UseProfile.class.getAnnotation(Profile.class).annotationType().getAnnotation(Conditional.class).value();

            for(Class<? extends Condition> condition : coditions ) {
                //反射实例化
                if(condition.newInstance().matches(values)) {
                    System.out.println("校验通过");
                } else {
                    System.out.println("校验失败");
                }
            }


        }

    }


}

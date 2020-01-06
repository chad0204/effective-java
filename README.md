没有哪个类是孤立的

### 第2章 创建和销毁对象

#### 静态工厂
#### 构建器
#### 单例

1.AccessibleObject.setAccessible方法可以利用反射机制调用私有构造器，处理方式可以在调用
构造器时抛出异常。

2.在实现序列化的类中，将属性定义成transient，可以防止反序列化导致的多实例。

3.只包含单个元素的枚举是最好的单例。

#### 清除对象

1.垃圾收集器不知道数组中的非活动部分(对象再也不会被使用，但是数组还持有对象的引用)。需要在
remove、pop等方法最后，将数组索引位置的对象引用置为null,让GC可以回收该对象。

2.不要使用终结方法和清除方法，他们不确定什么时候执行甚至不确定会执行，只能充当"安全网"。如果需要关闭资源，可以实现AutoCloseable,释放时
调用close方法。如OutputStream、Socket等。

### 第3章 对于所有对象都通用的方法

Object类包含的方法：getClass,clone,finalize,equals,hashCode,wait/wait(time),notify/notifyAll,toString

#### equals&hashCode

当值类有自己的逻辑相等概念，需要覆盖equals。

覆盖equals时总要覆盖hashCode，否则无法在hashMap和hashSet中运行。hashCode计算hash值定位桶，通过equals在发生hash冲突产生的链表中比较对象。
都不重写，值相同的对象是可以重复插入到hash中的。只重写equals,一样还是会插入重复元素，没有重写hashCode，equals无效。只重写hashCode,插
入值相同的对象相当于发生hash冲突，最终用equals(==)判断还是会插入。

equals相等hashCode必然相等（如果equals相等hashcode不等相当于值相等的元素落在不同的桶）；equals不相等hashCode结果不一定（equals不相等，hashCode相等说明发生
hash冲突，尽量让不相等的元素hashCode值也不相等是提高hash算法性能的关键）

#### clone

Cloneable接口是一个标记接口(Serializable也是)，作用是不实现该接口，Object的clone方法会抛出CloneNotSupportedException异常。

深拷贝就是对象包括对象包含的属性对象都存在两份。实现深拷贝的方式有clone方法、序列化和各种复制工具等。


#### Comparable&Comparator

几乎Java平台类库中的所有值类（值类只是一个表示值的类，例如Integer或String类）以及所有枚举类型都实现了Comparable接口。

### 第4章 类和接口

访问权限：public>protected(子类继承方法+包级访问)>default(包级访问)>private

#### 组合优先继承
继承覆盖了父类的方法，如果方法是自用的，例如父类的addAll调用父类的add方法，那么子类覆盖了这两个方法，父类调用子类的方法，导致重复。自如模式
的方法不能覆盖。

#### 继承
不要在构造器中调用能被覆盖的方法，可以调用final、私有和静态的方法。

#### 接口
接口的缺省方法相当于抽象父类的实现方法，子类不用重写可以直接公用（如果实现多个接口包含相同缺省方法，必须重写）。
不能为Object方法提供缺省方法。

### 第5章 范型



### 第6章 枚举和注解
 
### 第7章 lambda和stream

### 第8章 方法

### 第9章 通用编程

### 第10章 异常

### 第11章 并发

### 第12章 序列化

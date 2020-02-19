package com.pc.book.chapter4.inherit;

/**
 * 构造器不可调用能被覆盖的方法，因为被能被覆盖的方法在父类初始化时，子类该方法可能用到未初始化的属性。
 *
 * 构造器可以调用final,私有和静态的方法（这些方法不可覆盖）
 *
 * @author dongxie
 * @date 10:02 2020-01-06
 */
public class Super {

    public Super() {
        f();//构造器中调用了被覆盖的方法
    }

    public void f() {
        //该方法被子类覆盖
    }

}

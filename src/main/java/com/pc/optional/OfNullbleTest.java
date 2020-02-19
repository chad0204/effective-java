package com.pc.optional;

import java.util.Optional;

/**
 * 创建Optional的三种方式
 *
 * @author dongxie
 * @date 10:12 2019-12-26
 */
public class OfNullbleTest {

    public static void main(String[] args) {

        /**
         * Optional只有私有的构造器，只能通过下面三种方法创建对象
         */

        Model model = new Model();

        //内部使用Objects.requireNonNull(value)，如果对象为null会报NPE，否则返回带实例value的Optional容器
        Optional<Model> optionalModel = Optional.of(model);

        //对象为null相当于empty()，对象不为null相当于of()
        Optional<Model> optionalModel1 = Optional.ofNullable(model);

        //返回一个value为null的Optional容器
        Optional<Model> optionalModel2 = Optional.empty();


        /**
         * 结论：只有确定传入对象不为null,才使用of(),正常使用ofNullable
         */


    }

}

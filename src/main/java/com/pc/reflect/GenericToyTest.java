package com.pc.reflect;

public class GenericToyTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<FancyToy> fancyToyClass = FancyToy.class;

        FancyToy fancyToy = fancyToyClass.newInstance();//指定类型之后不需要用Object然后转型


        Class<? super FancyToy> up =  fancyToyClass.getSuperclass();

        //不能编译
//        Class<Toy> toyClass = fancyToyClass.getSuperclass();
        Object object = up.newInstance();//返回类不是确定类
    }
}

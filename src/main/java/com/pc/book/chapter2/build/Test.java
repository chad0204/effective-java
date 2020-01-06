package com.pc.book.chapter2.build;

import com.pc.book.chapter3.comparable.Person;

/**
 *
 *  不足之处：为了创建对象必须先创建该对象的构建器
 *
 * @author dongxie
 * @date 11:02 2019-12-19
 */
public class Test {

    public static void main(String[] args) {

        /*
        营养统计
            有两个必传参数需要在构造器中指定，可选参数通过构建器添加

         */
        NutritionFacts cocaCola = new NutritionFacts.NutritionFactsBuilder(250,5).calories(100).fat(10).build();

        System.out.println(cocaCola.getCalories());

        /*
        pizza
         */
        NyPizza nyPizza = new NyPizza
                .Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        CalzonePizza calzonePizza = new CalzonePizza.Builder().sauceInside().build();


        /*
        param
         */
        Param param = new Param.ParamBuilder().id(100L).name("lucy").type(1).build();
        System.out.println(param.getName());
        //可以将已存在的对象传入构建器进行修改
        Param changedParam = new Param.ParamBuilder(param).name("change").build();
        System.out.println(changedParam.getName());
        System.out.println(changedParam.getId());

    }


}

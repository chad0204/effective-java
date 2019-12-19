package com.pc.book.chapter2.build;

/**
 * 第一个构建器
 *  1.摒弃繁复的构造器和setter方法
 *  2.可以使对象保持不可变状态
 *
 *
 * @author dongxie
 * @date 09:36 2019-12-19
 */
public final class NutritionFacts {

    private final int servingSize;//大小
    private final int serving;//数量
    private final int calories;//碳水
    private final int fat;//油脂

    public static class NutritionFactsBuilder {
        //必选参数
        private final int servingSize;
        private final int serving;

        //可选参数
        private int calories;
        private int fat;

        public NutritionFactsBuilder(int servingSize, int serving) {
            this.servingSize = servingSize;
            this.serving = serving;
        }

        public NutritionFactsBuilder calories(int val) {
            this.calories = val;
            return this;
        }

        public NutritionFactsBuilder fat(int val) {
            this.fat = val;
            return this;
        }

        /**
         * 声明返回正确的子类
         */
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(NutritionFactsBuilder builder) {
        this.servingSize = builder.servingSize;
        this.serving = builder.serving;
        this.calories = builder.calories;
        this.fat = builder.fat;
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getServing() {
        return serving;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }


}

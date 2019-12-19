package com.pc.book.chapter2.build;

/**
 *
 * @author dongxie
 * @date 10:50 2019-12-19
 */
public class CalzonePizza extends Pizza {

    private final boolean sauceInside;


    public static class Builder extends Pizza.Builder {

        private boolean sauceInside = false;

        //加调料
        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        /**
         * 声明返回正确的子类
         */
        @Override
        CalzonePizza build() {
            return new CalzonePizza(this);
        }

        @Override
        protected Pizza.Builder self() {
            return this;
        }
    }

    CalzonePizza(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }
}

package com.pc.book.chapter2.build;

import java.util.Objects;

/**
 *
 *
 * @author dongxie
 * @date 10:42 2019-12-19
 */
public class NyPizza extends Pizza {

    public enum Size {
        SMALL,MEDIUM,LARGE
    }

    private final Size size;


    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }



    public NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}

package com.pc.book.chapter2.build;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * 类层次结构的构建器
 * 抽象pizza
 *
 * @author dongxie
 * @date 10:26 2019-12-19
 */
public abstract class Pizza {

    public enum Topping {HAM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        //self 为addTopping提供了可递归的添加参数
        protected abstract T self();

    }

    Pizza(Builder<?> builder) {
        this.toppings = builder.toppings.clone();//see item 50
    }
}
package com.pc.something;

import java.util.Collection;
import java.util.Set;

public class CountHashSet<E> extends DecoratorSet<E> {

    private int count = 0;


    public CountHashSet(Set<E> set) {
        super(set);
    }

    @Override
    public boolean add(E e) {
        count++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count+=c.size();
        return super.addAll(c);
    }
}

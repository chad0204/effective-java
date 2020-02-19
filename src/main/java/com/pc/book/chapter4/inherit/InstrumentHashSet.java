package com.pc.book.chapter4.inherit;

import java.util.Collection;
import java.util.Set;

public class InstrumentHashSet<E> extends ForwardingSet<E> {

    private int count = 0;


    public InstrumentHashSet(Set<E> set) {
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

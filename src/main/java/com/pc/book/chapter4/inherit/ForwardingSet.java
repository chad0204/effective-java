package com.pc.book.chapter4.inherit;

import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ForwardingSet<E> implements Set<E> {

    //注入set
    private final Set<E> s;

    private int count;

    public ForwardingSet(Set<E> s) { this.s = s; }


    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return s.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(T[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean add(E e) {
        count++;
        return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(0);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count+=c.size();
        return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return s.retainAll(c);
    }

    @Override
    public void clear() {
        s.clear();
    }

    public int getCount() {
        return count;
    }
}

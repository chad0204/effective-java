package com.pc.something;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {

    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }


    public E pop() {
        if(size==0)
            throw new EmptyStackException();
        E o = elements[--size];
        elements[size] = null;
        return o;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements,2*size+1);
    }
}

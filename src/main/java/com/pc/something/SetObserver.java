package com.pc.something;

/**
 * @author pengchao
 * @since 16:11 2019-09-06
 */
public interface SetObserver<E> {
    void added(ObservableSet<E> set,E element);
}

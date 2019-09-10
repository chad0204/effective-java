package com.pc.serialization;

import java.io.Serializable;

/**
 *
 * @author pengchao
 * @since 10:08 2019-09-10
 */
public final class StringListError implements Serializable {

    private int size = 0;
    private Entry head = null;

    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }
}

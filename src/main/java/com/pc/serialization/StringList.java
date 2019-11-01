package com.pc.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author pengchao
 * @see java.util.LinkedList
 * @see java.util.HashMap
 * @since 10:13 2019-09-10
 */
public final class StringList implements Serializable {
    private transient int size = 0;
    private transient Entry head = null;

    private static class Entry {
        String data;
        Entry next;
        Entry previous;//
    }

    public final void add(String s) {

    }

    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();
        os.writeInt(size);
        for(Entry e = head; e!=null; e=e.next) {
            os.writeObject(e.data);
        }
    }

    private void readObject(ObjectInputStream os) throws IOException, ClassNotFoundException {
        os.defaultReadObject();
        int numElements = os.readInt();
        for(int i=0; i< numElements; i++) {
           add((String) os.readObject());
        }
    }

}

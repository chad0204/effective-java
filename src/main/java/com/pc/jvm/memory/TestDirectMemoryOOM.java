package com.pc.jvm.memory;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * 测试直接内存OOM
 *
 *
 *
 * @author dongxie
 * @date 15:00 2020-04-27
 */
public class TestDirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0]; unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) { unsafe.allocateMemory(_1MB);//申请分配内存
        }
    }
}

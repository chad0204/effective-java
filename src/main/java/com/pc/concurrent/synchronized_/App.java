package com.pc.concurrent.synchronized_;

/**
 *
 *  进入target/classes/com/pc/concurrent/synchronized_
 *
 *       javap -v App,查看字节码文件
 *
 *
 *     public synchronized void synMethod();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC, ACC_SYNCHRONIZED
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: return
 *       LineNumberTable:
 *         line 132: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       1     0  this   Lcom/pc/concurrent/synchronized_/App;
 *
 *   public void synBlock();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: aload_0
 *          1: dup
 *          2: astore_1
 *          3: monitorenter
 *          4: aload_1
 *          5: monitorexit
 *          6: goto          14
 *          9: astore_2
 *         10: aload_1
 *         11: monitorexit
 *         12: aload_2
 *         13: athrow
 *         14: return
 *       Exception table:
 *          from    to  target type
 *              4     6     9   any
 *              9    12     9   any
 *       LineNumberTable:
 *         line 135: 0
 *         line 136: 4
 *         line 137: 14
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      15     0  this   Lcom/pc/concurrent/synchronized_/App;
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 255
 *           offset_delta = 9
 *           locals = [ class com/pc/concurrent/synchronized_/App, class java/lang/Object ]
 *           stack = [ class java/lang/Throwable ]
 *         frame_type = 250
 *           offset_delta = 4
 *
 *   public static synchronized void synStaticMethod();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
 *     Code:
 *       stack=0, locals=0, args_size=0
 *          0: return
 *       LineNumberTable:
 *         line 141: 0
 *
 */
public class App {

    public synchronized void synMethod() {//ACC_SYNCHRONIZED
    }

    public void synBlock() {
        /*
         * monitorenter
         * monitorexit
         */
        synchronized (this) {
        }
    }

    public static synchronized void synStaticMethod() {//ACC_SYNCHRONIZED

    }

}

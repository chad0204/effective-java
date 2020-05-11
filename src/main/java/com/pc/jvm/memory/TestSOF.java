package com.pc.jvm.memory;

/**
 * 测试StackOverflowError
 *
 *
 *
 *  java -Xss144k -jar jvm-dubbo-1.0-SNAPSHOT.jar
 *
 * @author dongxie
 * @date 10:43 2020-04-27
 */
public class TestSOF {

    private static int stackLength = -1;

    private void stackLeak() {//栈泄漏
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        TestSOF testSOF = new TestSOF();
        try {
            testSOF.stackLeak();
        } catch (Exception e) {
            System.out.println("stackLength:"+stackLength);
            throw e;
        }
    }
}

package com.pc.concurrent.collection;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author dongxie
 * @date 10:28 2020-05-16
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        //lock  head tail count ,不能保证head+count 或者tail+count 的原子性
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10);//必须有界
        LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(10);//有界，不传就是Integer.MAX_VALUE


        //cas 只需要考虑head和tail
        ConcurrentLinkedDeque<String> clq = new ConcurrentLinkedDeque<>();//无界
        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>();//无界



        abq.put("");
        abq.size();

        //count
        lbq.put("");
        lbq.add("");

        clq.push("");
        clq.add("");

        transferQueue.add("");
        transferQueue.size();



    }
}

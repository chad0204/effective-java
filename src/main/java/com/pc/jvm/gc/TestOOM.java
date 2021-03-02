package com.pc.jvm.gc;

import com.pc.VO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pengchao
 * @date 19:44 2021-02-24
 */
public class TestOOM {

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];//80M


    public static void main(String[] args) throws InterruptedException {
        new TestOOM().deadCycleII();

    }


    public void deadCycle() throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        list.add(new byte[8 * _10MB]);

        for (int i = 0; i < list.size(); i ++) {
            TimeUnit.MILLISECONDS.sleep(1000L);
            if (i == list.size() - 1) {
                list.add(new byte[8 * _10MB]);
            }
        }
    }


    public void deadCycleII() throws InterruptedException {
        List<VO> list = new ArrayList<>();
        list.add(new VO("begin","name"));

        for (int i = 0; i < list.size(); i ++) {
            TimeUnit.MILLISECONDS.sleep(10L);
            if (i == list.size() - 1) {
                list.add(new VO(""+i,""+i));
            }
        }
    }
}

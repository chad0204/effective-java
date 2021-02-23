package com.pc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pengchao
 * @date 11:00 2021-02-18
 */
public class OOM {

    /**
     * 这是一段死循环代码
     */
    public void oom() {
        List<VO> list = new ArrayList<>();
        list.add(new VO("begin","name"));

        for (int i = 0; i < list.size(); i ++) {
            if (i == list.size() - 1) {
                list.add(new VO(""+i,""+i));
            }
        }
    }
}

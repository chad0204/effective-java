package com.pc.concurrent.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-06-02 3:30 下午
 */
public class Test {



}



class GetTopic implements Runnable {



    @Override
    public void run() {




    }
}


class releaseTopic implements Runnable {

    @Override
    public void run() {

    }
}

class TopicSource {

    public static Map<String,String>  topics = new HashMap<>();

    static {
        topics.put("topic1","");
        topics.put("topic2","");
        topics.put("topic3","");
    }


}





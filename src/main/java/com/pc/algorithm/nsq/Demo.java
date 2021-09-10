//package com.pc.algorithm.nsq;
//
//import com.github.brainlag.nsq.NSQConsumer;
//import com.github.brainlag.nsq.NSQMessage;
//import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
//import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
//import com.github.brainlag.nsq.lookup.NSQLookup;
//import java.util.concurrent.Executor;
//
///**
// * 测试NsqConsumer
// *
// * @author pengchao
// * @date 01:10 2021-04-18
// */
//public class Demo {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        Demo demo = new Demo();
//
//        demo.nsqConsumer();
//
//
//        Thread.sleep(10000);
//
//    }
//
//    //    消费者：
//    public  void nsqConsumer(){
//        NSQLookup lookup = new DefaultNSQLookup();
////        外网ip地址 、lookup的端口号
//        lookup.addLookupAddress("127.0.0.1", 4161);
////        lookup、topic名称、订阅的消息
//        NSQConsumer consumer = new NSQConsumer(lookup, "test", "nsq_to_file", new NSQMessageCallback(){
//            @Override
//            public void message(NSQMessage message) {
////                获取订阅消息的内容
//                byte b[] = message.getMessage();
//                String s = new String(b);
//                System.out.println(s);
//                message.finished();
//            }
//        });
//        consumer.start();
////线程睡眠，让程序执行完
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        consumer.setExecutor(new Executor() {
//            @Override
//            public void execute(Runnable command) {
//                // TODO Auto-generated method stub
//            }
//        });
//    }
//}

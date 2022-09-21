package com.pc.concurrent.future.dubbofuture;

/**
 *
 * @author dongxie
 * @date 09:28 2020-05-11
 */
public class Test {


    public static void main(String[] args) {

        Request request = new Request();
        request.setData("get");

        DefaultFuture future = new DefaultFuture(request);

        //FutureFilter中实现
        future.setCallback(new ResponseCallback() {
            @Override
            public void done(Object response) {
                System.out.println("callback result:"+response.toString());
            }

            @Override
            public void caught(Throwable exception) {
                System.out.println("callback error:"+exception);
            }
        });


        new Thread(future).start();


        System.out.println("主线程可以做点别的什么事情");

        //
        System.out.println("get result:"+future.get(40000));



    }
}

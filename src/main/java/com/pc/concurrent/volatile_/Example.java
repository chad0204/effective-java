package com.pc.concurrent.volatile_;

import lombok.SneakyThrows;

public class Example {

    public static void main(String[] args) throws InterruptedException {
        Example example = new Example();

        Thread start = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                example.start();
            }
        });
        start.start();

        Thread init = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(100);
                example.init();
            }
        });
        init.start();

        start.join();
        init.join();

    }



    private volatile boolean initialized;
    private Config config;

    public void init() throws InterruptedException {
        //volatile保证这里不会指令重排
        config = readFromFile();
        initialized = true;
    }

    public void start() throws InterruptedException {
        while(!initialized){
            //volatile保证这里的可见性, 即initialized被另一个线程修改后立即读到.
        }
        doSomethingWithConfig();
    }

    private void doSomethingWithConfig() {
        config.id.longValue();
        System.out.println("success");
    }

    private Config readFromFile() throws InterruptedException {
        return new Config(100L);
    }

    class Config {
        private Long id;
        public Config(Long id) throws InterruptedException {
            Thread.sleep(1000);
            this.id = id;
        }
    }
}

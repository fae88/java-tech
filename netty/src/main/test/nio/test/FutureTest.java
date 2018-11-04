package com.gemini.cloud.service.netty.nio.test;

import java.util.concurrent.*;

public class FutureTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void test() {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("begin callable");
                while(true) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("fufan");
                }
            }
        };
        Future<String> future = executorService.submit(callable);
        Future<String> future2 = executorService.submit(() -> {return "bb";});
        try {
            System.out.println("aa");
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public  String test2() {
        while (true) {
            System.out.println("aa");
        }
    }

    public static void main(String[] args) {
        new FutureTest().test();
    }
}

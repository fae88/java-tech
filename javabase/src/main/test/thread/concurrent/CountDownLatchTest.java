package thread.concurrent;

import java.util.concurrent.*;

public class CountDownLatchTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(4);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        FutureTask<Object> taobaoTask = new FutureTask<>(new Task() {
            @Override
            public Object doQuery() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return "taobao";
            }
        });

        FutureTask<Object> carrierTask = new FutureTask<>(new Task() {
            @Override
            public Object doQuery() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return "carrier";
            }
        });

        FutureTask<Object> magicwandTask = new FutureTask<>(new Task() {
            @Override
            public Object doQuery() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return "magicwand";
            }
        });

        FutureTask<Object> bankTask = new FutureTask<>(new Task() {
            @Override
            public Object doQuery() {
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return "bank";
            }
        });


        executorService.submit(taobaoTask);
        executorService.submit(carrierTask);
        executorService.submit(bankTask);
        executorService.submit(magicwandTask);

        countDownLatch.await(7, TimeUnit.SECONDS);

        System.out.println("7秒超时时间到");

        System.out.println(taobaoTask.isDone());
        System.out.println(carrierTask.isDone());
        System.out.println(bankTask.isDone());
        System.out.println(magicwandTask.isDone());

        System.out.println("afdsfdsa".substring(20, 32));

        executorService.shutdown();


    }


}

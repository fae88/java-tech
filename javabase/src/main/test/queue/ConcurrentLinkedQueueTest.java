package queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

    private static int size = 1000;
    private static int count = 2;

    private static CountDownLatch countDownLatch = new CountDownLatch(count);

    static Runnable runnable = () -> {
        queue.poll();
        countDownLatch.countDown();
    };

    static {
        for (int i = 0; i < size; i ++) {
            queue.add(i);
        }
    }

    public static void main(String[] args) {


        ExecutorService es = Executors.newFixedThreadPool(4);

        for (int i = 0; i < count; i ++ ) {
            es.submit(runnable);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(queue.size());

        es.shutdown();

    }
}

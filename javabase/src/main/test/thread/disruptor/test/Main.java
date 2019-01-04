package thread.disruptor.test;

import java.util.concurrent.*;

public class Main {
    public static final int QUEUE_SIZE = 100;

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newCachedThreadPool();

        BlockingQueue<Order> blockingQueue = new ArrayBlockingQueue<Order>(QUEUE_SIZE);

        Producer producer = new Producer(blockingQueue);
        new Thread(producer).start();
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(consumer).start();


    }
}

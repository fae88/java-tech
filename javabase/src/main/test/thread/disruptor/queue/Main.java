package thread.disruptor.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        // 初始化阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1000);
        // 创建生产者线程
        Thread producer = new Thread(new Producer(blockingQueue, "/Users/fufan/draft/tmp/temp.dat"));
        producer.start();
        // 创建消费者线程
        Thread consumer = new Thread(new Consumer(blockingQueue));
        consumer.start();
    }
}
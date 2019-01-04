package thread.disruptor.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

    BlockingQueue<Order> blockingQueue;

    boolean closed = false;

    public Consumer(BlockingQueue<Order> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (!closed) {
            if (blockingQueue.peek() != null) {
                System.out.println("poll an order : " + blockingQueue.poll().getId());
                System.out.println("now, this queue total size is : " + blockingQueue.size());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("queue has no orders");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        closed = true;
    }

}

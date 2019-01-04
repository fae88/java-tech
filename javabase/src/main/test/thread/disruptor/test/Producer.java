package thread.disruptor.test;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable{

    BlockingQueue<Order> blockingQueue;

    boolean closed = false;

    public Producer(BlockingQueue<Order> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (!closed) {
            while (blockingQueue.size() < 100) {
                Order order = new Order();
                String id = UUID.randomUUID().toString();
                order.setId(id);
                offer(order);
                System.out.println("put an order into queue: " + id);
                System.out.println("queue size is : " + blockingQueue.size());
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("queue is full , please wait now");
        }
    }


    public boolean offer(Order order) {
        return blockingQueue.offer(order);
    }

    public void shutdown() {
        closed = true;
    }
}

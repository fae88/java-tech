package queue;


import java.util.concurrent.CountDownLatch;

public class TestMyQueue {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        MyQueue myQueue = new MyQueue(5);

        Thread thread1 = new Thread(() -> {
            myQueue.add("h");
            myQueue.add("i");
            myQueue.add("j");
            myQueue.add("a");
            myQueue.add("b");
            myQueue.add("c");
            myQueue.add("e");
            myQueue.add("f");
            myQueue.add("g");

            countDownLatch.countDown();

        });

        Thread thread2 = new Thread(() -> {
            Object o1 = myQueue.poll();
            System.out.println(String.format("o1: %s", o1));
            Object o2 = myQueue.poll();
            System.out.println(String.format("o2: %s", o2));
            Object o3 = myQueue.poll();
            System.out.println(String.format("o3: %s", o3));

            countDownLatch.countDown();
        });

        thread1.start();
        thread2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myQueue.getSize());
    }
}

package thread.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SynchronizedTest {


    @Test
    public void testAddOne() throws InterruptedException {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for(int i = 0; i < 100000; i ++) {
                counter.addOne();
            }
            System.out.println(System.currentTimeMillis() - startTime);
        });

        Thread t2 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for(int i = 0; i < 100000; i ++) {
                counter.decreaseOne();
            }
            System.out.println(System.currentTimeMillis() - startTime);
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        TimeUnit.SECONDS.sleep(1);

        System.out.println(counter.getValue());
    }

}


class Counter {

    static volatile int value;

    public int getValue() {
        return value;
    }

    public  synchronized void addOne() {
        value = value + 1;
    }

    public  synchronized void decreaseOne() {
        value = value - 1;
    }
}


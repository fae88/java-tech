package thread;

import java.util.concurrent.TimeUnit;

public class Producer {

    private Object lock;
    boolean available = true;

    Producer(Object lock) {
        this.lock = lock;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Object produce() {

        try {

            synchronized (lock) {

                System.out.println("get it");
//                while (available) {
                lock.wait();
//                }
                return new Object();
            }
        }catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }

    public void comsume() {
        try {

            synchronized (lock) {

//                while (available) {
                Thread.sleep(2000);
                lock.notify();
//                }
                System.out.println("comsume it");
            }
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer(new Object());

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("%s begin", Thread.currentThread().getName()));
                producer.produce();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("%s begin", Thread.currentThread().getName()));
                producer.comsume();
            }
        };

        new Thread(runnable1).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(runnable2).start();


//        new Thread(runnable).start();
    }

}

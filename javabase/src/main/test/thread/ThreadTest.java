package thread;

import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) {

        ThreadTest threadTest = new ThreadTest();

        new Thread(() -> {
            threadTest.test();
        }).start();

        System.out.println("between 2 threads");
//
//        new Thread(() -> {
//            threadTest.test2();
//        }).start();
    }


    public void test() {

        synchronized (this) {
            try {
                wait(5000);
                System.out.println("get notify" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
//                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after wait()");
        }
    }

    public void test2() {

        synchronized (this) {
            try {

                System.out.println("begin: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after wait()");

        }
    }

}

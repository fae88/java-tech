package thread;

import java.util.concurrent.TimeUnit;

public class TwoThreadPrintNum {

    public static boolean flag = true;

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {


            @Override
            public void run() {

                synchronized (TwoThreadPrintNum.class) {
                    while (true) {

                        for(int i = 0; i < 100; i = i + 2) {
                            try {
                                if (TwoThreadPrintNum.flag) {
//                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.println(i);
                                    TwoThreadPrintNum.flag = false;
                                    TwoThreadPrintNum.class.notify();
                                } else {
                                    i = i - 2;
                                    TwoThreadPrintNum.class.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }
            }
        };

        Runnable runnable2 = new Runnable() {


            @Override
            public void run() {

                synchronized (TwoThreadPrintNum.class) {
                    while (true) {
                        for(int i = 1; i < 100; i = i + 2) {
                            try {
                                if (!TwoThreadPrintNum.flag) {
//                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.println(i);
                                    TwoThreadPrintNum.flag = true;
                                    TwoThreadPrintNum.class.notify();
                                } else {
                                    i = i - 2;
                                    TwoThreadPrintNum.class.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();


    }



}

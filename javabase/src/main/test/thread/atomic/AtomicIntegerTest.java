package thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private AtomicInteger atomicInteger = new AtomicInteger();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            atomicInteger.set(10);
        }
    };


    public static void main(String[] args) {


    }
}

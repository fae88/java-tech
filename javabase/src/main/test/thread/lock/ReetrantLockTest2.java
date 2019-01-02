package thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试锁超时
 */
@Slf4j
public class ReetrantLockTest2 {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(3, TimeUnit.SECONDS)) {
                        TimeUnit.SECONDS.sleep(2);
                        log.info("get lock thread {}", Thread.currentThread().getName()) ;
                    } else {
                        log.error("get lock error thread {}", Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(2, TimeUnit.SECONDS)) {
                        TimeUnit.SECONDS.sleep(2);
                        log.info("get lock thread {}", Thread.currentThread().getName()) ;
                    } else {
                        log.error("get lock error thread {}", Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}

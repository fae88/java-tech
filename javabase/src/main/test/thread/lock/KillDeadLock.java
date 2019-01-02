package thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class KillDeadLock implements Runnable{

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public KillDeadLock(int lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            log.error("{} interrupted", Thread.currentThread().getName());
        } finally {
            if (lock1.isHeldByCurrentThread()) lock1.unlock();  // 注意判断方式
            if (lock2.isHeldByCurrentThread()) lock2.unlock();
            log.error( "退出 {}", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        KillDeadLock deadLock1 = new KillDeadLock(1);
        KillDeadLock deadLock2 = new KillDeadLock(2);
        Thread t1 = new Thread(deadLock1, "t1");
        Thread t2 = new Thread(deadLock2, "t2");
        t1.start();t2.start();
        Thread.sleep(3000);
        t2.interrupt();
    }
}

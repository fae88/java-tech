package thread.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    lock.lock();
                    log.info("into lock thread1");
                    TimeUnit.SECONDS.sleep(3);
                    condition.await();
                    log.info("get CPU again lock time: {}", lock.getHoldCount());
                    TimeUnit.SECONDS.sleep(3);
                    lock.lock();
                    log.info("get CPU again 2 lock time: {}", lock.getHoldCount());
                } catch (Exception e){
                    log.error("error", e);
                }finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    log.info("into lock thread2");
                    TimeUnit.SECONDS.sleep(3);
                    condition.signal();
                } catch (Exception e){
                    log.error("error", e);
                }finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();

    }
}

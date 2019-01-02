package queue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手动实现控制并发的队列，FIFO
 */
public class MyQueue {

    //=======================================变量==================================//

    // 通过linkedlist结构来存放队列
    LinkedList<Object> linkedList = new LinkedList<>();

    //通过atomic来计数
    AtomicInteger count = new AtomicInteger(0);

    // 队列的上限和下限
    private final int minSize = 0;

    private final int maxSize;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    //内置锁对象来控制并发处理队列
    private final Object lock = new Object();



    //=======================================方法==================================//


    // 塞入一个元素
    public void add(Object o) {

        synchronized (lock) {
            while (this.count.get() == maxSize) {
                try {
                    // 循环判断队列是否已满，若满，则释放锁。
                    lock.wait();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 添加元素
            linkedList.add(o);

            // 队列数量++
            count.incrementAndGet();

            // 操作完毕，线程结束，通知其他线程
            lock.notify();
        }
    }

    // 弹出第一个一个元素
    public Object poll() {

        synchronized (lock) {
            while (this.count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 取出元素
            Object o = linkedList.removeFirst();

            count.decrementAndGet();

            lock.notify();

            return o;
        }
    }

    /**
     * 获取队列大小
     * @return
     */
    public int getSize() {
        return this.count.get();
    }

}

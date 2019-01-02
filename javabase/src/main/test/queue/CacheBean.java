package queue;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CacheBean<V> {
    // 缓存计算的结果
    private final static ConcurrentMap<String, Future<Object>> cache = new ConcurrentHashMap<>();

    // 延迟队列来判断那些缓存过期
    private final static DelayQueue<DelayedItem<String>> delayQueue = new DelayQueue<>();

    // 缓存时间
    private final int ms;

    static {
        // 定时清理过期缓存
        Thread t = new Thread() {
            @Override
            public void run() {
                dameonCheckOverdueKey();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    private final Computable<V> c;

    /**
     * @param c Computable
     */
    public CacheBean(Computable<V> c) {
        this(c, 60 * 1000);
    }

    /**
     * @param c Computable
     * @param ms 缓存多少毫秒
     */
    public CacheBean(Computable<V> c, int ms) {
        this.c = c;
        this.ms = ms;
    }

    public V compute(final String key) throws InterruptedException {

        while (true) {
            //根据key从缓存中获取值
            Future<V> f = (Future<V>) cache.get(key);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    public V call() {
                        return (V) c.compute(key);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                //如果缓存中存在此可以，则返回已存在的value
                f = (Future<V>) cache.putIfAbsent(key, (Future<Object>) ft);
                if (f == null) {
                    //向delayQueue中添加key，并设置该key的存活时间
                    delayQueue.put(new DelayedItem<>(key, ms));
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(key, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查过期的key，从cache中删除
     */
    private static void dameonCheckOverdueKey() {
        DelayedItem<String> delayedItem;
        while (true) {
            try {
                delayedItem = delayQueue.take();
                if (delayedItem != null) {
                    cache.remove(delayedItem.getT());
                    System.out.println(System.nanoTime() + " remove " + delayedItem.getT() + " from cache");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DelayedItem<T> implements Delayed {

    private T t;
    private long liveTime;
    private long removeTime;

    public DelayedItem(T t, long liveTime) {
        this.setT(t);
        this.liveTime = liveTime;
        this.removeTime = TimeUnit.MILLISECONDS.convert(liveTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null)
            return 1;
        if (o == this)
            return 0;
        if (o instanceof DelayedItem) {
            DelayedItem<T> tmpDelayedItem = (DelayedItem<T>) o;
            if (liveTime > tmpDelayedItem.liveTime) {
                return 1;
            } else if (liveTime == tmpDelayedItem.liveTime) {
                return 0;
            } else {
                return -1;
            }
        }
        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return diff > 0 ? 1 : diff == 0 ? 0 : -1;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(removeTime - System.currentTimeMillis(), unit);
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public int hashCode() {
        return t.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof DelayedItem) {
            return object.hashCode() == hashCode() ? true : false;
        }
        return false;
    }

}
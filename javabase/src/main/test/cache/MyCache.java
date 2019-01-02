package cache;

import java.util.concurrent.*;

public class MyCache<V> {


    private ConcurrentHashMap<Object, Future<V>> cacheMap = new ConcurrentHashMap<>();

//    private DelayQueue<>


}

/**
 * 定义延迟队列中的对象
 * @param <T>
 */
class DelayItem<T> implements Delayed {


    T t;    // 存储的对象
    long liveTime;  // 当前剩余存活时间
    long delayTime;    // 到期时间

    DelayItem(T t, long liveTime) {
        setT(t);
        this.liveTime = liveTime;
        this.delayTime = TimeUnit.MILLISECONDS.convert(liveTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
    }

    /**
     * 返回的就是liveTime
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayTime - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {

        if (o == null) {
            return 1;
        }
        if (t == o) {
            return 0;
        }
        return 0;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}


/**
 * 定义如何获取到数据的计算接口
 * @param <V>
 */
interface Computable<V> {

    public V compute(Object o);
}
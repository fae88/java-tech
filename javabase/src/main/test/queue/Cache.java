package queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Cache<K, V> {

    private ConcurrentHashMap<K, V> concurrentHashMap = new ConcurrentHashMap<>();

    private DelayQueue<Entry<K, V>> delayQueue = new DelayQueue<>();

    public Cache<K, V> cache = new Cache<>();

    public Cache<K, V> getCache() {
        return cache;
    }

    /**
     * 通过k获取v
     * @param k
     * @return
     */
    public V get(K k) {
        return concurrentHashMap.get(k);
    }

    public void put(K k, V v, TimeUnit unit) {
        V value = concurrentHashMap.get(k);

        if (value != null) {
            concurrentHashMap.remove(k);
        }

        concurrentHashMap.put(k, v);


    }



    public class Entry<K, V> implements Delayed {

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}

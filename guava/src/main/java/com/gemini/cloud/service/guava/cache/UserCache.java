package com.gemini.cloud.service.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UserCache {

    private LoadingCache<String[], User> loadingCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String[], User>() {
                @Override
                public User load(String[] s) throws Exception {
                    return getUser(s[0]);
                }
            });


    private User getUser(String key) {

        User user1 = new User();
        User user2 = new User();

        user1.setId("bbc1");
        user1.setUsername("ccd1");

        user2.setId("bbc2");
        user2.setUsername("cc2");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return user1;
    }


    /**
     * 测试cacheload的key是否可以为对象
     * 测试结果：cacheload的对象不能为对象，guava在对象的比对上不会比对对象的内容，也就是equals和==的问题，他选择了对象==。
     *         如果不是基本类型的话，会导致每次缓存失效，如果时间设置的够长，会导致内存泄漏（即内存中无用数据积压）
     * @param args
     * @throws ExecutionException
     */
    public static void main(String[] args) throws ExecutionException {

        UserCache userCache = new UserCache();
        String[] arrays = {"bbc", "aab"};
        String[] arrays2 = {"bbc", "aab"};

        long start1 = System.currentTimeMillis();
        User user1 = userCache.loadingCache.get(arrays);
        log.info("first query user1: time: {}, userId: {}", System.currentTimeMillis() - start1, user1.getId());

        long start2 = System.currentTimeMillis();
        User user2 = userCache.loadingCache.get(arrays2);
        log.info("first query user2: time: {}, userId: {}", System.currentTimeMillis() - start2, user2.getId());
    }
}

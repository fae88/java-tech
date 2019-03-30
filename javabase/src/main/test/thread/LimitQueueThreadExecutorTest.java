package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class LimitQueueThreadExecutorTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setNameFormat("test-thread-%d");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                threadFactoryBuilder.build(),new CustomRejectedExecutionHandler());



        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("ok");
            return "kk";
        });

        futureTask.run();
        System.out.println(futureTask.get());

        Runnable task = () -> {
            System.out.println("test");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i=0; i < 10; i ++) {
            threadPoolExecutor.submit(task);
            TimeUnit.SECONDS.sleep(1);
        }


    }
    public static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        }
    }



}

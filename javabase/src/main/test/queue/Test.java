package queue;

import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("bbbb");
        };

        for (int i = 0; i < 3; i ++) {
            ExecutorService executorService = new ThreadPoolExecutor(3, 3,
                    1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

            for (int j = 0; j < 10; j ++) {
                executorService.submit((runnable));
                ((ThreadPoolExecutor) executorService).getQueue().size();
            }
        }



    }
}

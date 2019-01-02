package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 即使线程出现问题，可以通过线程池管理线程，重新启动线程来执行任务
 *
 * 这里展示的是一个计算1+2+...100的计算，在加到50的时候抛出异常，然后再catch中重新启动一个线程继续执行任务
 *
 */
public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            int count = 1;
            int sum = 0;
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                        if(count == 101){
                            break;
                        }
                        if (count == 50){
                            sum = sum + count;
                            count ++;
                            throw new RuntimeException("我是异常");
                        }
                        sum = sum + count;
                        System.out.println("##count:"+count+",sum:"+sum);
                        count ++;
                    }catch (Exception e){
                        e.printStackTrace();
                        //注意这个地方
                        System.out.println("重启一个线程，继续执行");
                        executorService.execute(this);
                    }
                }
            }
        });

//        executorService.shutdown();
    }
}
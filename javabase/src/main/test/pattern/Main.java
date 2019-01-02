package pattern;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        
        Master master = new Master(new Worker(), 20);//并发数20，也就是有20个Worker在工作
        
        Random r = new Random();
        for(int i = 1; i <= 100; i++){//总共有100个任务
            Task t = new Task();
            t.setId(i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);//提交任务，向WorkerQueue<Task> 中加入元素
        }
        master.execute();//启动所有的worker
        long start = System.currentTimeMillis();
        
        while(!master.isComplete()){
            long end = System.currentTimeMillis() - start;
            int priceResult = master.getResult();//获取所有任务的执行结果
            System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
        }
        
    }
}
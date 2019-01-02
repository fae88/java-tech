package thread.disruptor.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<String> blockingQueue;
    private static final String FINIDHED = "EOF";

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String line;
        String[] arrStr;
        int ret;
        try {
            while (!(line = blockingQueue.take()).equals(FINIDHED)) {
                // 消费
                arrStr = line.split("\t");
                if (arrStr.length != 2) {
                    continue;
                }
                ret = Integer.parseInt(arrStr[0]) + Integer.parseInt(arrStr[1]);
                System.out.println(ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
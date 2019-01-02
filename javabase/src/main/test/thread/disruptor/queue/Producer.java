package thread.disruptor.queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<String> blockingQueue;
    private String fileName;
    private static final String FINIDHED = "EOF";

    public Producer(BlockingQueue<String> blockingQueue, String fileName)  {
        this.blockingQueue = blockingQueue;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                blockingQueue.put(line);
            }
            // 结束标志
            blockingQueue.put(FINIDHED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package thread.disruptor.disruptor;

import com.lmax.disruptor.EventFactory;

public class DisruptorFactory implements EventFactory<FileData> {
    @Override
    public FileData newInstance() {
        return new FileData();
    }
}

package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class NIOClient {

    public static void main(String[] args){
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",3333));
            ArrayList<String> messages = new ArrayList<String>();
            messages.add("hello");
            messages.add("world");
            messages.add("my name");
            messages.add("quanshijie");
            messages.add("end");

            for (String message : messages) {
                byte[] b = new String(message).getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(b);
                socketChannel.write(buffer);
                buffer.clear();
                Thread.sleep(3000);
            }
            new Thread(new ReadWork(socketChannel)).start();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    static class ReadWork implements Runnable{

        SocketChannel socketChannel;

        public ReadWork(SocketChannel socketChannel){
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            while (true){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (true){
                    try {
                        while (socketChannel.read(buffer) != -1){
                            System.err.println("啊哈，我收到服务端的消息---->"+new String(buffer.array()).trim());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

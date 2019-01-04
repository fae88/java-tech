package http.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Main {

    public static final String PATH = "/Users/fufan/draft/tmp/test";


    public static void main(String args[]) throws IOException {
//        String relativelyPath = System.getProperty("user.dir");
        FileInputStream input = new FileInputStream(PATH + "/testin.txt");
        ReadableByteChannel source = input.getChannel();
        FileOutputStream output = new FileOutputStream(PATH + "/testout.txt");
        WritableByteChannel destination = output.getChannel();
        copyData(source, destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }
}

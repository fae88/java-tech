package http.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferTest {

    public static Logger LOGGER = LoggerFactory.getLogger(BufferTest.class);

    public static final String PATH = "/Users/fufan/draft/tmp/test";

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = null;

        try {
            Path filePath = Paths.get(PATH + "/testout.txt");
            InputStream inputStream = Files.newInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println("testout.txt content: " + str);
            }
        } catch (Exception e) {
            LOGGER.error("io error", e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ioe) {
                LOGGER.error("io error ", ioe);
            }
        }




    }
}

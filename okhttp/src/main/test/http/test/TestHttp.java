package http.test;

import com.gemini.cloud.service.okhttp.concurrencytest.util.OkhttpUtil;
import com.gemini.cloud.service.okhttp.util.HttpclientUtils;
import com.gemini.cloud.service.okhttp.util.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class TestHttp {

    public static final String TEST_URL = "http://baidu.com";

    @Test
    public void testHttpclient() {
        try {
            String result = HttpclientUtils.getHttpclientUtils().get(TEST_URL);
            log.info(result);
        } catch (IOException e) {
            log.error("IO exception", e);
        }
    }

    @Test
    public void testOkHttp() {

        String result = null;
        try {
            result = OkHttpUtils.getInstance().get(TEST_URL);
        } catch (IOException e) {
            log.error("IO exception", e);
        }
        log.info(result);
    }


    @Test
    public void testOkhttpUtils() {
        OkhttpUtil.getInstance().init(200, 10);
        System.out.println(OkhttpUtil.getInstance().doGet(TEST_URL));
    }

    @Test
    public void test() {

        for( int i=0; i<100000; i ++) {
            System.out.println("http://baidu.com");
        }
    }

}

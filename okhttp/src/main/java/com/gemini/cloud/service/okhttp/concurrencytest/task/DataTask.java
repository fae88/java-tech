package com.gemini.cloud.service.okhttp.concurrencytest.task;

import com.gemini.cloud.service.okhttp.concurrencytest.util.FileUtil;
import com.gemini.cloud.service.okhttp.concurrencytest.util.OkhttpUtil;

/**
 * ---------------------------------------------------------------
 * Author: perseverance.li
 * ---------------------------------------------------------------
 */
public class DataTask implements Runnable {

    private String url;

    public DataTask(String url) {
        this.url = url;
    }

    @Override
    public void run() {

        String result = OkhttpUtil.getInstance().doGet(url);
        //TODO:结果写入文件
        FileUtil.writeFile(result + "\r\n", "/Users/fufan/draft/tmp/result.dat");
    }
}
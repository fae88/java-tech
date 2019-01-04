package com.gemini.cloud.service.okhttp.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtils {

    OkHttpClient client;

    public static OkHttpUtils okHttpUtils = new OkHttpUtils();
    public static OkHttpUtils getInstance() {return okHttpUtils;}

    public OkHttpUtils() {
        init();
    }

    public void init() {
        ConnectionPool pool = new ConnectionPool(5, 10, TimeUnit.MINUTES);
        client = new OkHttpClient.Builder() //
                .connectTimeout(3, TimeUnit.MINUTES) //
                .followRedirects(true) //
                .readTimeout(3, TimeUnit.MINUTES) //
                .retryOnConnectionFailure(false) //
                .writeTimeout(3, TimeUnit.MINUTES)
                .connectionPool(pool) //
                .build();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            log.error("io exception");
        }
        Headers responseHeaders = response.headers();
//
//        for (int i = 0; i < responseHeaders.size(); i++) {
//            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//        }
        return response.body().string();
    }
}

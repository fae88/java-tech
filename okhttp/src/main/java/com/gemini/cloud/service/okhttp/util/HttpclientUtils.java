package com.gemini.cloud.service.okhttp.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

@Slf4j
public class HttpclientUtils {

    private CloseableHttpClient client;


    public static HttpclientUtils httpclientUtils = new HttpclientUtils();

    public static HttpclientUtils getHttpclientUtils() {
        return httpclientUtils;
    }

    public HttpclientUtils () {
        init();
    }

    public void init() {

        // 链接池
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(2000);
        clientConnectionManager.setDefaultMaxPerRoute(100);

        // RequestConfig
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(10000).
                setConnectionRequestTimeout(10000).
                setSocketTimeout(10000).
                setExpectContinueEnabled(false)
                .build();

        // ConnectionConfig
        ConnectionConfig connectionConfig = ConnectionConfig.custom().
                setCharset(Charset.forName("UTF-8")).build();
        // SocketConfig
        SocketConfig socketConfig = SocketConfig.custom().
                setSoKeepAlive(true).setTcpNoDelay(false).build();

        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 1) {
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    return true;
                } else if (exception instanceof ClientProtocolException) {
                    return true;
                } else return exception instanceof SocketTimeoutException;
            }
        };

        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator
                        (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase
                            ("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return 60 * 1000;//如果没有约定，则默认定义时长为60s
            }
        };

        client = HttpClients.custom()
                .setConnectionManager(clientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultConnectionConfig(connectionConfig)
                .setDefaultSocketConfig(socketConfig)
                .setRetryHandler(retryHandler)
                .setKeepAliveStrategy(myStrategy)
                .build();
    }

    public String get(String url) throws IOException {

        HttpGet method = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(method);
        } catch (IOException e) {
            log.info("action=sendGet url:{}, occur exception", url, e);
            return null;
        }

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            log.error("action=sendGet, return code not equal 200. code:{}", statusCode);
            return null;
        }
        // Read the response body
        return EntityUtils.toString(response.getEntity());
    }


}

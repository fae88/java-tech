package com.gemini.cloud.service.guava.cache.test.observer;

public class Test {


    @org.junit.Test
    public void test() throws InterruptedException {

        Poster poster = new Poster("toutiao");

        Poster poster2 = new Poster("wangyi");

        for( int i = 0; i < 10; i ++) {
            poster.addObserver(new Reader("fufan" + i));
            poster2.addObserver(new Reader("fufan" + i));
        }

        poster.post("chinese footbool team lose again");
        poster2.post("chinese olympic team outgoing line");
    }
}

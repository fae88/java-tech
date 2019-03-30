package com.gemini.cloud.service.guava.cache.test.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Poster extends Observable {

    String name;

    public Poster() {}

    public Poster(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void post(String news) throws InterruptedException {

        setChanged();
        notifyObservers(news);
        TimeUnit.SECONDS.sleep(1);
    }
}

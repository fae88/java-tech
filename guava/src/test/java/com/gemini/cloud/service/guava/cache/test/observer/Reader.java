package com.gemini.cloud.service.guava.cache.test.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

@Slf4j
public class Reader implements Observer {


    String name;

    public Reader(){}

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {

        String name = o.toString();

        if (o instanceof Poster) {
            name = ((Poster) o).getName();
        }

        log.info("{} post {} , {} go for reading it" , name ,arg, this.name);

    }

}

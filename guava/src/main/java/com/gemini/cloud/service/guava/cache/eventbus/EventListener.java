package com.gemini.cloud.service.guava.cache.eventbus;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {      
        return lastMessage;
    }
}
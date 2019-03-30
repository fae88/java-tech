package com.gemini.cloud.service.guava.cache.test;

import com.gemini.cloud.service.guava.cache.eventbus.EventListener;
import com.gemini.cloud.service.guava.cache.eventbus.EventListener2;
import com.gemini.cloud.service.guava.cache.eventbus.TestEvent;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

public class TestEventBus {



    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        EventListener2 listener2 = new EventListener2();

        eventBus.register(listener);
        eventBus.register(listener2);

        eventBus.post(10);
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());
    }
}
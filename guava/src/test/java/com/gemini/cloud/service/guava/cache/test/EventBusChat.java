package com.gemini.cloud.service.guava.cache.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.gemini.cloud.service.guava.cache.eventbus.UserThread;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

public class EventBusChat {

    @Test
    public void test() {
        EventBus channel = new EventBus();
        ServerSocket socket;
        try {
            socket = new ServerSocket(4444);
            while (true) {
                Socket connection = socket.accept();
                UserThread newUser = new UserThread(connection, channel);
                channel.register(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
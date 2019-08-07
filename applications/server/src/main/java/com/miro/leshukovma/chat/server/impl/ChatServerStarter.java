package com.miro.leshukovma.chat.server.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatServerStarter implements ApplicationListener<ApplicationReadyEvent> {

    private final ChatServer chatServer;

    public void onApplicationEvent(ApplicationReadyEvent applicationEvent) {
        try {
            chatServer.start();
        } catch (Exception e) {
            throw new RuntimeException("Unable to start chat server", e);
        }
    }

}

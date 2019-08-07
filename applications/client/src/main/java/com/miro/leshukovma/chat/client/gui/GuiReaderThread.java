package com.miro.leshukovma.chat.client.gui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class GuiReaderThread implements Runnable {

    private StringBuffer buffer = new StringBuffer();

    @Autowired
    private UserInputDispatcher userInputDispatcher;

    public void run() {
        try {
            InputStreamReader userInputStream = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            while (!Thread.interrupted()) {
                int userInputChar = userInputStream.read();
                if (userInputChar != '\n') {
                    buffer.appendCodePoint(userInputChar);
                } else {
                    String userInput = buffer.toString();
                    userInputDispatcher.dispatch(userInput);
                    buffer = new StringBuffer();
                }
            }
        } catch (IOException e) {
            log.warn("Error", e);
        }
    }

}

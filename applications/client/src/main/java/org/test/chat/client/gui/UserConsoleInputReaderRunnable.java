package org.test.chat.client.gui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserConsoleInputReaderRunnable implements Runnable {

    @Autowired
    protected UserInputHandlerKeeper userInputHandlerKeeper;
    @Autowired
    protected ConsoleReaderProvider consoleReaderProvider;


    private StringBuffer buffer = new StringBuffer();
    protected final InputStream systemIn;


    public UserConsoleInputReaderRunnable() {
        systemIn = System.in;
    }

    public void run() {
        try {
            Reader consoleReader = consoleReaderProvider.getConsoleReader();
            Reader userInputStream =  consoleReader != null ? consoleReader : new InputStreamReader(systemIn, StandardCharsets.UTF_8);
            while (!Thread.interrupted()) {
                int userInputChar = userInputStream.read();
                if (userInputChar != '\n') {
                    buffer.appendCodePoint(userInputChar);
                } else {
                    String userInput = buffer.toString();
                    userInputHandlerKeeper.dispatch(userInput);
                    buffer = new StringBuffer();
                }
            }
        } catch (IOException e) {
            log.warn("Error", e);
        }
    }

}

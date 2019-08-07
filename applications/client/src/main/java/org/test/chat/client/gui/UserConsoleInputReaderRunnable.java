package org.test.chat.client.gui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class UserConsoleInputReaderRunnable implements Runnable {

    private StringBuffer buffer = new StringBuffer();

    @Autowired
    private UserInputHandlerKeeper userInputHandlerKeeper;

    public void run() {
        try {
            Console console = System.console();
            Reader userInputStream =  console != null ? console.reader() : new InputStreamReader(System.in, StandardCharsets.UTF_8);
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

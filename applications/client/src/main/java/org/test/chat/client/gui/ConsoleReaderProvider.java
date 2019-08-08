package org.test.chat.client.gui;

import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.Reader;

@Service
public class ConsoleReaderProvider {

    public Reader getConsoleReader() {
        Console console = System.console();
        return console != null ? console.reader() : null;
    }

}

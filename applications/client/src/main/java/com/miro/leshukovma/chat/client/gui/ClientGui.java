package com.miro.leshukovma.chat.client.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ClientGui {

    @Autowired
    private GuiReaderThread guiReaderThread;


    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future readerFuture;

    @PostConstruct
    public void startReadUserInput() {
        readerFuture = executor.submit(guiReaderThread);
    }

    @PreDestroy
    public void stopReadUserInput() {
        readerFuture.cancel(true);
    }
}

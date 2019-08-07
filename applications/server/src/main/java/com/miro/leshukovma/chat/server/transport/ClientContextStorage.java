package com.miro.leshukovma.chat.server.transport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientContextStorage {

    private final ThreadLocal<ClientContext> threadLocalContext = new ThreadLocal<>();

    public ClientContext get() {
        ClientContext clientContext = threadLocalContext.get();
        if (clientContext == null) {
            throw new RuntimeException("Client context is empty");
        }
        return clientContext;
    }

    public void set(ClientContext ctx) {
        threadLocalContext.set(ctx);
    }

}

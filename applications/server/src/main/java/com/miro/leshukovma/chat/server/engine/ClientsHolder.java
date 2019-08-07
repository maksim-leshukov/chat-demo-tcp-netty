package com.miro.leshukovma.chat.server.engine;

import com.miro.leshukovma.chat.server.client.ClientContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;

@Slf4j
@Service
public class ClientsHolder {

    private ConcurrentHashMap<String, ClientContext> clientLogin2context = new ConcurrentHashMap<>();

    private final AtomicLong successLoginClientCount = new AtomicLong();

    public boolean addAs(String login, ClientContext client, BiConsumer<String, ClientContext> onSuccessLogin) {
        AtomicBoolean insertedNew = new AtomicBoolean(false);
        clientLogin2context.computeIfAbsent(login, currentLogin ->  {
            successLoginClientCount.incrementAndGet();
            onSuccessLogin.accept(login, client);
            insertedNew.set(true);
            return client;
        });

        long successLoginCount = successLoginClientCount.get();
        if (successLoginCount % 1000 == 0) {
            log.info("{} success logging", successLoginCount);
        }

        return insertedNew.get();
    }

    public void remove(String login) {
        clientLogin2context.remove(login);
    }

    public Collection<String> getUserLogins() {
        return clientLogin2context.keySet();
    }

    public Collection<ClientContext> getClients() {
        return clientLogin2context.values();
    }
}

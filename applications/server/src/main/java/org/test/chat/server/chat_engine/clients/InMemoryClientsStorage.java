package org.test.chat.server.chat_engine.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.test.chat.server.transport.ClientContext;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

@Slf4j
@Service
public class InMemoryClientsStorage implements ClientsStorage {

    private ConcurrentMap<String, ClientContext> clientLogin2context = new ConcurrentHashMap<>();

    @Override
    public boolean tryAddAs(String login, ClientContext client, BiConsumer<String, ClientContext> onSuccessLogin) {
        AtomicBoolean insertedNew = new AtomicBoolean(false);
        clientLogin2context.computeIfAbsent(login, currentLogin ->  {
            onSuccessLogin.accept(login, client);
            insertedNew.set(true);
            return client;
        });

        return insertedNew.get();
    }


    @Override
    public void remove(String login) {
        clientLogin2context.remove(login);
    }


    @Override
    public Collection<String> getClientLogins() {
        return clientLogin2context.keySet();
    }


    @Override
    public Collection<ClientContext> getClients() {
        return clientLogin2context.values();
    }


}

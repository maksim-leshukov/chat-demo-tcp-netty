package com.miro.leshukovma.chat.server.chat_engine.clients;

import com.miro.leshukovma.chat.server.transport.ClientContext;

import java.util.Collection;
import java.util.function.BiConsumer;

public interface ClientsStorage {

    /**
     * @param onSuccessLogin call when adding is success
     * @return adding is success or not
     */
    boolean tryAddAs(String login, ClientContext client, BiConsumer<String, ClientContext> onSuccessLogin);

    void remove(String login);

    Collection<String> getClientLogins();

    Collection<ClientContext> getClients();

}

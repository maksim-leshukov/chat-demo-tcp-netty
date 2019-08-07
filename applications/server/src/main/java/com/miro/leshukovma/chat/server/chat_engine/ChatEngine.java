package com.miro.leshukovma.chat.server.chat_engine;

import com.miro.leshukovma.chat.server.transport.ClientContext;

public interface ChatEngine {

    void loginAs(String login, ClientContext client);

    void logout(ClientContext client);

    void createMessage(String senderUsername, String message);

}

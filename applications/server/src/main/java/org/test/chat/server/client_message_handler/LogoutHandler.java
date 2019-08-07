package org.test.chat.server.client_message_handler;

import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_server.Logout;
import org.test.chat.server.chat_engine.ChatEngine;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientContextStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutHandler implements PayloadMessageHandler<Logout> {

    @Autowired
    private ChatEngine chatEngine;
    @Autowired
    private ClientContextStorage contextStorage;

    @Override
    public void onMessage(Logout message) {
        ClientContext client = contextStorage.get();
        chatEngine.logout(client);
    }

}

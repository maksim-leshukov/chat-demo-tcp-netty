package org.test.chat.server.client_message_handler;

import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_server.SendMessage;
import org.test.chat.server.chat_engine.ChatEngine;
import org.test.chat.server.transport.ClientContextStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageHandler implements PayloadMessageHandler<SendMessage> {

    @Autowired
    private ChatEngine chatEngine;
    @Autowired
    private ClientContextStorage contextStorage;

    public void onMessage(SendMessage message) {
        String senderUsername = contextStorage.get().getLogin();
        chatEngine.createMessage(senderUsername, message.getText());
    }

}

package com.miro.leshukovma.chat.server.handler;

import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_server.SendMessage;
import com.miro.leshukovma.chat.server.client.ClientContextStorage;
import com.miro.leshukovma.chat.server.engine.ChatEngine;
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
        chatEngine.onUserMessage(senderUsername, message.getText());
    }

}

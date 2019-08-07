package com.miro.leshukovma.chat.client.handler;

import com.miro.leshukovma.chat.common.message.to_client.NewMessage;
import org.springframework.stereotype.Component;

@Component
public class NewMessageHandler extends AbstractNewMessageHandler<NewMessage> {



    public void onMessage(NewMessage message) {
        onNewMessage(message);
    }

}

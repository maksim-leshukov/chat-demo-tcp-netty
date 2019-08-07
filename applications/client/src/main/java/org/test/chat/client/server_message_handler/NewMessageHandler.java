package org.test.chat.client.server_message_handler;

import org.test.chat.common.message.to_client.NewMessage;
import org.springframework.stereotype.Component;

@Component
public class NewMessageHandler extends AbstractNewMessageHandler<NewMessage> {



    public void onMessage(NewMessage message) {
        onNewMessage(message);
    }

}

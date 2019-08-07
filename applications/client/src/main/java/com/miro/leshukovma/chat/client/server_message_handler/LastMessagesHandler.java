package com.miro.leshukovma.chat.client.server_message_handler;

import com.miro.leshukovma.chat.common.message.to_client.LastMessages;
import org.springframework.stereotype.Component;

@Component
public class LastMessagesHandler extends AbstractNewMessageHandler<LastMessages> {

    public void onMessage(LastMessages lastMessages) {
        userDialogService.print("Last messages:");
        lastMessages.getMessages()
                .forEach(this::onNewMessage);
    }

}

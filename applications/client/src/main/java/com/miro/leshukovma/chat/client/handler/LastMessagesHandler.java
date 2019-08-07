package com.miro.leshukovma.chat.client.handler;

import com.miro.leshukovma.chat.common.message.to_client.LastMessages;
import org.springframework.stereotype.Component;

@Component
public class LastMessagesHandler extends AbstractNewMessageHandler<LastMessages> {

    public void onMessage(LastMessages lastMessages) {
        userDialogService.print("Last message:");
        lastMessages.getMessages()
                .forEach(this::onNewMessage);
    }

}

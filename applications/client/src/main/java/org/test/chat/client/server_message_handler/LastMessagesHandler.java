package org.test.chat.client.server_message_handler;

import org.test.chat.common.message.to_client.LastMessages;
import org.springframework.stereotype.Component;

@Component
public class LastMessagesHandler extends AbstractNewMessageHandler<LastMessages> {

    public void onMessage(LastMessages lastMessages) {
        userDialogService.print("Last messages:");
        lastMessages.getMessages()
                .forEach(this::onNewMessage);
    }

}

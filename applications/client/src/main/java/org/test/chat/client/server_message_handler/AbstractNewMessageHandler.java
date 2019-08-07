package org.test.chat.client.server_message_handler;

import org.test.chat.client.gui.UserDialogService;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_client.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNewMessageHandler<MessageType extends PayloadMessage> implements PayloadMessageHandler<MessageType> {

    @Autowired
    protected UserDialogService userDialogService;

    protected void onNewMessage(ChatMessageDto message) {
        userDialogService.print(message.getDate() + " " + message.getAuthor() + ": " + message.getMessage());
    }
}

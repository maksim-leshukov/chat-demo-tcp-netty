package com.miro.leshukovma.chat.client.handler;

import com.miro.leshukovma.chat.client.gui.UserDialogService;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_client.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNewMessageHandler<MessageType extends PayloadMessage> implements PayloadMessageHandler<MessageType> {

    @Autowired
    protected UserDialogService userDialogService;

    protected void onNewMessage(ChatMessageDto message) {
        userDialogService.print(message.getDate() + " " + message.getAuthor() + ": " + message.getMessage());
    }
}

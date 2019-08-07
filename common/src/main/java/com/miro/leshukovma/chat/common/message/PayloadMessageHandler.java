package com.miro.leshukovma.chat.common.message;

public interface PayloadMessageHandler<MessageType extends PayloadMessage> {

    void onMessage(MessageType message);

}

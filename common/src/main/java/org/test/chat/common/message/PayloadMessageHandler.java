package org.test.chat.common.message;

public interface PayloadMessageHandler<MessageType extends PayloadMessage> {

    void onMessage(MessageType message);

}

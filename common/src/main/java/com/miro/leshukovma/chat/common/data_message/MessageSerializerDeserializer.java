package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;

public interface MessageSerializerDeserializer {

    void registerType(DataMessageType messageType, Class<? extends PayloadMessage> messageClass);

    String serialize(PayloadMessage message);

    <PayloadMessageType extends PayloadMessage> PayloadMessageType deserialize(DataMessageType type, String message);

}

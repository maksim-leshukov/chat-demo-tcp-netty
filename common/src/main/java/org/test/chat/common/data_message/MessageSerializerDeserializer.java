package org.test.chat.common.data_message;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;

public interface MessageSerializerDeserializer {

    void registerType(DataMessageType messageType, Class<? extends PayloadMessage> messageClass);

    String serialize(PayloadMessage message);

    <PayloadMessageType extends PayloadMessage> PayloadMessageType deserialize(DataMessageType type, String message);

}

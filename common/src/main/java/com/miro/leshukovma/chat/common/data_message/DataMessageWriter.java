package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataMessageWriter {


    @Autowired
    private MessageSerializerDeserializer serializerDeserializer;

    private Map<Class, DataMessageType> class2type = new HashMap<>();

    public void write(ChannelHandlerContext ctx, PayloadMessage payloadMessage) {
        DataMessage dataMessage = new DataMessage();

        DataMessageType type = getType(payloadMessage.getClass());
        dataMessage.setType(type);

        String serializedMessage = serializerDeserializer.serialize(payloadMessage);
        dataMessage.setPayload(serializedMessage);

        ctx.writeAndFlush(dataMessage);
    }

    private DataMessageType getType(Class payloadMessageClass) {
        DataMessageType type = class2type.get(payloadMessageClass);
        if (type == null) {
            PayloadMessageType messageTypeAnnotation = (PayloadMessageType) payloadMessageClass.getDeclaredAnnotation(PayloadMessageType.class);
            type = messageTypeAnnotation.value();
            class2type.put(payloadMessageClass, type);
        }
        return type;
    }
}

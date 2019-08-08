package org.test.chat.common.tcp;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.data_message.DataMessage;
import org.test.chat.common.data_message.MessageSerializerDeserializer;
import org.test.chat.common.data_message.PayloadMessageDataTypeFetcher;
import org.test.chat.common.message.PayloadMessage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TcpDataMessageWriter {


    @Autowired
    private MessageSerializerDeserializer serializerDeserializer;
    @Autowired
    protected PayloadMessageDataTypeFetcher dataTypeFetcher;

    private ConcurrentMap<Class, DataMessageType> class2type = new ConcurrentHashMap<>();

    public void write(ChannelHandlerContext ctx, PayloadMessage payloadMessage) {
        DataMessage dataMessage = new DataMessage();

        DataMessageType type = getType(payloadMessage.getClass());
        dataMessage.setType(type);

        String serializedMessage = serializerDeserializer.serialize(payloadMessage);
        dataMessage.setPayload(serializedMessage);

        ctx.writeAndFlush(dataMessage);
    }

    private DataMessageType getType(Class payloadMessageClass) {
        return class2type.computeIfAbsent(payloadMessageClass, payloadClass -> dataTypeFetcher.getDataMessageType(payloadClass));
    }
}

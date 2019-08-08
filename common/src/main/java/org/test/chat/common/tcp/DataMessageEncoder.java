package org.test.chat.common.tcp;

import org.test.chat.common.data_message.DataMessage;
import org.test.chat.common.data_message.DataMessageSerializerDeserializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Slf4j
@Service
@ChannelHandler.Sharable
public class DataMessageEncoder extends MessageToByteEncoder<DataMessage> {

    @Autowired
    protected DataMessageSerializerDeserializer serializerDeserializer;



    @Override
    protected void encode(ChannelHandlerContext ctx, DataMessage dataMessage, ByteBuf out) {
        String json = serializerDeserializer.serialize(dataMessage);
        log.trace("Write '{}'", json);

        out.writeCharSequence(json + "\n", Charset.forName("UTF-8"));
    }
}

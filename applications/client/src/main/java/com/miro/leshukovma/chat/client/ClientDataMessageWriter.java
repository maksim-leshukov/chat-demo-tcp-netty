package com.miro.leshukovma.chat.client;

import com.miro.leshukovma.chat.common.data_message.DataMessageWriter;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import io.netty.channel.ChannelHandlerContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDataMessageWriter {

    @Autowired
    private DataMessageWriter dataMessageWriter;

    @Setter
    private ChannelHandlerContext ctx;


    public void write(PayloadMessage payloadMessage) {
        dataMessageWriter.write(ctx, payloadMessage);
    }
}

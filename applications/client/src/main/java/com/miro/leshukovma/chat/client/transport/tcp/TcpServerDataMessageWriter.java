package com.miro.leshukovma.chat.client.transport.tcp;

import com.miro.leshukovma.chat.client.transport.ServerDataMessageWriter;
import com.miro.leshukovma.chat.common.transport.tcp.TcpDataMessageWriter;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import io.netty.channel.ChannelHandlerContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpServerDataMessageWriter implements ServerDataMessageWriter {

    @Autowired
    private TcpDataMessageWriter dataMessageWriter;

    @Setter
    private ChannelHandlerContext ctx;


    public void write(PayloadMessage payloadMessage) {
        dataMessageWriter.write(ctx, payloadMessage);
    }
}

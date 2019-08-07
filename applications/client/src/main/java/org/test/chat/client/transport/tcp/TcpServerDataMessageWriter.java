package org.test.chat.client.transport.tcp;

import org.test.chat.client.transport.ServerDataMessageWriter;
import org.test.chat.common.tcp.TcpDataMessageWriter;
import org.test.chat.common.message.PayloadMessage;
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

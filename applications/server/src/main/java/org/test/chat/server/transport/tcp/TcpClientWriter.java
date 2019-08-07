package org.test.chat.server.transport.tcp;

import org.test.chat.common.tcp.TcpDataMessageWriter;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientContextStorage;
import org.test.chat.server.transport.ClientWriter;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpClientWriter implements ClientWriter {

    @Autowired
    private TcpDataMessageWriter dataMessageWriter;
    @Autowired
    private ClientContextStorage clientContextStorage;


    @Override
    public void write(ClientContext clientContext, PayloadMessage payloadMessage) {
        TcpServerClientContext tcpContext = (TcpServerClientContext) clientContext;
        dataMessageWriter.write(tcpContext.getTransportContext(), payloadMessage);
    }

    /**
     * Write message to client which request is handling
     */
    @Override
    public void write(PayloadMessage payloadMessage) {
        TcpServerClientContext tcpContext = (TcpServerClientContext) clientContextStorage.get();
        ChannelHandlerContext transport = tcpContext.getTransportContext();
        dataMessageWriter.write(transport, payloadMessage);
    }

}

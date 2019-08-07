package com.miro.leshukovma.chat.server.transport.tcp;

import com.miro.leshukovma.chat.common.data_message.DataMessageWriter;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.server.transport.ClientContext;
import com.miro.leshukovma.chat.server.transport.ClientContextStorage;
import com.miro.leshukovma.chat.server.transport.ClientWriter;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpClientWriter implements ClientWriter {

    @Autowired
    private DataMessageWriter dataMessageWriter;
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

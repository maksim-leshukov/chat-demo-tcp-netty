package com.miro.leshukovma.chat.server.client;

import com.miro.leshukovma.chat.common.data_message.DataMessageWriter;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerDataMessageWriter {

    @Autowired
    private DataMessageWriter dataMessageWriter;
    @Autowired
    private ClientContextStorage clientContextStorage;


    /**
     * Write message to specific client
     */
    public void write(ClientContext clientContext, PayloadMessage payloadMessage) {
        dataMessageWriter.write(clientContext.getTransportContext(), payloadMessage);
    }

    /**
     * Write message to client which request is handling
     */
    public void write(PayloadMessage payloadMessage) {
        ChannelHandlerContext transport = clientContextStorage.get().getTransportContext();
        dataMessageWriter.write(transport, payloadMessage);
    }

}

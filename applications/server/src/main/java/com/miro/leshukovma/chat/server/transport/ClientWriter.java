package com.miro.leshukovma.chat.server.transport;

import com.miro.leshukovma.chat.common.message.PayloadMessage;

public interface ClientWriter {

    /**
     * Write message to specific client
     */
    void write(ClientContext clientContext, PayloadMessage payloadMessage);


    /**
     * Write message to client which request is handling
     */
    void write(PayloadMessage payloadMessage);

}

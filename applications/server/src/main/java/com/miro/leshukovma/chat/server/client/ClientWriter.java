package com.miro.leshukovma.chat.server.client;

import com.miro.leshukovma.chat.common.data_message.DataMessageWriter;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientWriter {

    @Autowired
    private DataMessageWriter dataMessageWriter;

    public void printMessage(ClientContext clientContext, PayloadMessage message) {
        dataMessageWriter.write(clientContext.getTransportContext(), message);
    }
}

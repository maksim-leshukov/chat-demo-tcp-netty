package com.miro.leshukovma.chat.client.transport;

import com.miro.leshukovma.chat.common.message.PayloadMessage;

public interface ServerDataMessageWriter {

    void write(PayloadMessage payloadMessage);

}

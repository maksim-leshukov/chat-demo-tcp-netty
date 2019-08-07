package org.test.chat.client.transport;

import org.test.chat.common.message.PayloadMessage;

public interface ServerDataMessageWriter {

    void write(PayloadMessage payloadMessage);

}

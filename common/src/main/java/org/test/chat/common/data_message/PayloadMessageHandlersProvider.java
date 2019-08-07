package org.test.chat.common.data_message;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessageHandler;

public interface PayloadMessageHandlersProvider {

    PayloadMessageHandler getHandler(DataMessageType type);

}

package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;

public interface PayloadMessageHandlersProvider {

    PayloadMessageHandler getHandler(DataMessageType type);

}

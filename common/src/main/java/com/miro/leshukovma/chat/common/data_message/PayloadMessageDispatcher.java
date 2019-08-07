package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayloadMessageDispatcher {

    @Autowired
    private MessageSerializerDeserializer messageDeserializer;
    @Autowired
    private PayloadMessageHandlersKeeper handlersKeeper;


    public void dispatch(DataMessage dataMessage) {
        log.debug("Dispatch {}", dataMessage);
        DataMessageType type = dataMessage.getType();
        String payloadJson = dataMessage.getPayload();
        PayloadMessage payloadMessage = messageDeserializer.deserialize(type, payloadJson);

        PayloadMessageHandler payloadMessageHandler = handlersKeeper.getHandler(type);
        payloadMessageHandler.onMessage(payloadMessage);
    }

}

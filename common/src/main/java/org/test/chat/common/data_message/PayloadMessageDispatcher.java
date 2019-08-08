package org.test.chat.common.data_message;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayloadMessageDispatcher {

    @Autowired
    protected MessageSerializerDeserializer messageDeserializer;
    @Autowired
    protected PayloadMessageHandlersProvider handlersProvider;


    public void dispatch(DataMessage dataMessage) {
        log.debug("Dispatch {}", dataMessage);
        DataMessageType type = dataMessage.getType();
        String payloadJson = dataMessage.getPayload();
        PayloadMessage payloadMessage = messageDeserializer.deserialize(type, payloadJson);

        PayloadMessageHandler payloadMessageHandler = handlersProvider.getHandler(type);
        payloadMessageHandler.onMessage(payloadMessage);
    }

}

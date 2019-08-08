package org.test.chat.common.data_message;

import org.springframework.stereotype.Service;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

@Service
public class PayloadMessageDataTypeFetcher {

    public DataMessageType getDataMessageType(Class<? extends PayloadMessage> payloadMessageClass) {
        PayloadMessageType messageTypeAnnotation = payloadMessageClass.getDeclaredAnnotation(PayloadMessageType.class);
        if (messageTypeAnnotation == null) {
            throw new RuntimeException("Payload message " + payloadMessageClass + " does not have annotation " + PayloadMessageType.class);
        }
        return messageTypeAnnotation.value();
    }

}

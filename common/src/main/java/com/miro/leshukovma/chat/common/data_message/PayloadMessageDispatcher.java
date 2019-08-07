package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayloadMessageDispatcher {

    @Autowired
    private MessageSerializerDeserializer messageDeserializer;
    @Autowired
    private List<PayloadMessageHandler> handlers;


    private final Map<DataMessageType, PayloadMessageHandler> type2handler = new HashMap<>();

    @PostConstruct
    public void initMap() {
        for (PayloadMessageHandler handler : handlers) {
            Class<? extends PayloadMessageHandler> handlerClass = handler.getClass();
            Type[] handlerInterfaces = handlerClass.getGenericInterfaces();

            ParameterizedType handlerInterface = handlerInterfaces.length != 0 ? (ParameterizedType) handlerInterfaces[0] : (ParameterizedType) handlerClass.getGenericSuperclass();
            Class<? extends PayloadMessage> payloadMessageType = (Class<? extends PayloadMessage>) handlerInterface.getActualTypeArguments()[0];

            PayloadMessageType messageTypeAnnotation = payloadMessageType.getDeclaredAnnotation(PayloadMessageType.class);
            if (messageTypeAnnotation == null) {
                throw new RuntimeException("Message class " + payloadMessageType + " handled by " + handler + " does not have annotation " + PayloadMessageType.class);
            }

            DataMessageType messageType = messageTypeAnnotation.value();

            type2handler.put(messageType, handler);
            messageDeserializer.registerType(messageType, payloadMessageType);
        }

    }


    public void dispatch(DataMessage dataMessage) {
        log.debug("Dispatch {}", dataMessage);
        DataMessageType type = dataMessage.getType();
        String payload = dataMessage.getPayload();
        PayloadMessage payloadMessage = messageDeserializer.deserialize(type, payload);

        PayloadMessageHandler payloadMessageHandler = getHandler(type);
        payloadMessageHandler.onMessage(payloadMessage);
    }

    private PayloadMessageHandler getHandler(DataMessageType type) {
        PayloadMessageHandler handler = type2handler.get(type);
        if (handler == null) {
            throw new RuntimeException("Handler for type " + type + " not found");
        }
        return handler;
    }
}

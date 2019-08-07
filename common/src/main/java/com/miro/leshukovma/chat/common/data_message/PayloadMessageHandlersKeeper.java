package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayloadMessageHandlersKeeper {

    @Autowired
    private MessageSerializerDeserializer messageDeserializer;
    @Autowired
    private List<PayloadMessageHandler> handlers;


    private final Map<DataMessageType, PayloadMessageHandler> messageType2handler = new HashMap<>();

    @PostConstruct
    public void initMessageHandlersMap() {
        for (PayloadMessageHandler handler : handlers) {
            Class<? extends PayloadMessageHandler> handlerClass = handler.getClass();
            Class<? extends PayloadMessage> payloadMessageType = getPayloadMessageClass(handlerClass);

            PayloadMessageType messageTypeAnnotation = payloadMessageType.getDeclaredAnnotation(PayloadMessageType.class);
            if (messageTypeAnnotation == null) {
                throw new RuntimeException("Message class " + payloadMessageType + " handled by " + handler + " does not have annotation " + PayloadMessageType.class);
            }

            DataMessageType messageType = messageTypeAnnotation.value();

            messageType2handler.put(messageType, handler);
            messageDeserializer.registerType(messageType, payloadMessageType);
        }

    }

    private Class<? extends PayloadMessage> getPayloadMessageClass(Class handlerClass) {
        Type[] handlerInterfaces = handlerClass.getGenericInterfaces();

        ParameterizedType handlerInterface = handlerInterfaces.length != 0
                ? (ParameterizedType) handlerInterfaces[0]
                : (ParameterizedType) handlerClass.getGenericSuperclass();
        return (Class<? extends PayloadMessage>) handlerInterface.getActualTypeArguments()[0];
    }


    public PayloadMessageHandler getHandler(DataMessageType type) {
        PayloadMessageHandler handler = messageType2handler.get(type);
        if (handler == null) {
            throw new RuntimeException("Handler for type " + type + " not found");
        }
        return handler;
    }
}

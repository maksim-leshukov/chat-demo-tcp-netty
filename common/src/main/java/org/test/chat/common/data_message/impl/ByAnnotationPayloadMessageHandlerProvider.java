package org.test.chat.common.data_message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.data_message.MessageSerializerDeserializer;
import org.test.chat.common.data_message.PayloadMessageDataTypeFetcher;
import org.test.chat.common.data_message.PayloadMessageHandlersProvider;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageHandler;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ByAnnotationPayloadMessageHandlerProvider implements PayloadMessageHandlersProvider {

    @Autowired
    protected MessageSerializerDeserializer messageDeserializer;
    @Autowired
    protected List<PayloadMessageHandler> handlers;
    @Autowired
    protected PayloadMessageDataTypeFetcher dataTypeFetcher;


    private final Map<DataMessageType, PayloadMessageHandler> messageType2handler = new HashMap<>();

    @PostConstruct
    public void initMessageHandlersMap() {
        for (PayloadMessageHandler handler : handlers) {
            Class<? extends PayloadMessageHandler> handlerClass = handler.getClass();
            Class<? extends PayloadMessage> payloadMessageType = getPayloadMessageClass(handlerClass);

            DataMessageType messageType = dataTypeFetcher.getDataMessageType(payloadMessageType);

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


    @Override
    public PayloadMessageHandler getHandler(DataMessageType type) {
        PayloadMessageHandler handler = messageType2handler.get(type);
        if (handler == null) {
            throw new RuntimeException("Handler for type " + type + " not found");
        }
        return handler;
    }
}

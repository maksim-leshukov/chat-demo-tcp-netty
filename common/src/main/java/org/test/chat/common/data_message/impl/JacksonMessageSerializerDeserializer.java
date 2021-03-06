package org.test.chat.common.data_message.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.data_message.MessageSerializerDeserializer;
import org.test.chat.common.message.PayloadMessage;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JacksonMessageSerializerDeserializer implements MessageSerializerDeserializer {

    private Map<DataMessageType, ObjectReader> payloadMessageType2reader = new HashMap<>();
    private ConcurrentHashMap<Class, ObjectWriter> payloadMessageType2writer = new ConcurrentHashMap<>();


    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Override
    public void registerType(DataMessageType messageType, Class<? extends PayloadMessage> messageClass) {
        payloadMessageType2reader.put(messageType, objectMapper.readerFor(messageClass));
    }


    @Override
    @SneakyThrows
    public String serialize(PayloadMessage message) {
        Class payloadMessageType = message.getClass();
        ObjectWriter objectWriter = payloadMessageType2writer.computeIfAbsent(payloadMessageType, objectMapper::writerFor);
        return objectWriter.writeValueAsString(message);
    }


    @Override
    public <PayloadMessageType extends PayloadMessage> PayloadMessageType deserialize(DataMessageType type, String message) {
        ObjectReader objectReader = payloadMessageType2reader.get(type);
        if (objectReader == null) {
            throw new RuntimeException("Message class for type " + type + " not registered. Try to add handler for message with that type.");
        }

        try {
            return objectReader.readValue(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

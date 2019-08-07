package com.miro.leshukovma.chat.common.data_message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataMessageSerializerDeserializer {

    private final ObjectWriter serializer;
    private final ObjectReader deserializer;


    public DataMessageSerializerDeserializer() {
        ObjectMapper mapper = new ObjectMapper();

        serializer = mapper.writerFor(DataMessage.class);
        deserializer = mapper.readerFor(DataMessage.class);
    }


    @SneakyThrows
    public String serialize(DataMessage msg) {
        return serializer.writeValueAsString(msg);
    }


    public DataMessage deserialize(String json) throws IOException {
        return deserializer.readValue(json);
    }

}

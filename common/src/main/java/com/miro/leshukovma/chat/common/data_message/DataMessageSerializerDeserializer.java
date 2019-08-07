package com.miro.leshukovma.chat.common.data_message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataMessageSerializerDeserializer {

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = mapper.writerFor(DataMessage.class);
    private ObjectReader reader = mapper.readerFor(DataMessage.class);


    @SneakyThrows
    public String serialize(DataMessage msg) {
        return writer.writeValueAsString(msg);
    }


    public DataMessage deserialize(String json) throws IOException {
        return reader.readValue(json);
    }

}

package org.test.chat.common.data_message;

import org.junit.Test;
import org.test.chat.common.DataMessageType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DataMessageSerializerDeserializerTest {

    private DataMessageSerializerDeserializer serializerDeserializer = new DataMessageSerializerDeserializer();

    @Test
    public void testSerialize() {
        DataMessage dataMessage = new DataMessage();
        dataMessage.setType(DataMessageType.LoginAs);
        dataMessage.setPayload("serialize payload");

        String actualResult = serializerDeserializer.serialize(dataMessage);

        assertEquals("{\"type\":\"LoginAs\",\"payload\":\"serialize payload\"}", actualResult);
    }

    @Test
    public void testDeserialize() throws IOException {
        DataMessage dataMessage = serializerDeserializer.deserialize("{\"type\":\"LoginAccepted\",\"payload\":\"deserialize payload\"}");

        assertEquals(DataMessageType.LoginAccepted, dataMessage.getType());
        assertEquals("deserialize payload", dataMessage.getPayload());
    }

}
package org.test.chat.common.data_message.impl;

import org.junit.Assert;
import org.junit.Test;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.to_server.LoginAs;

import static org.junit.Assert.*;

public class JacksonMessageSerializerDeserializerTest {

    private JacksonMessageSerializerDeserializer serializerDeserializer = new JacksonMessageSerializerDeserializer();

    @Test
    public void serialize() {
        String json = serializerDeserializer.serialize(new LoginAs("user1"));

        Assert.assertEquals("{\"login\":\"user1\"}", json);
    }

    @Test
    public void deserialize() {
        serializerDeserializer.registerType(DataMessageType.LoginAs, LoginAs.class);
        LoginAs loginAs = serializerDeserializer.deserialize(DataMessageType.LoginAs, "{\"login\":\"user2\"}");

        Assert.assertNotNull(loginAs);
        Assert.assertEquals("user2", loginAs.getLogin());
    }

    @Test(expected = RuntimeException.class)
    public void deserializeNotRegisteredType() {
        serializerDeserializer.deserialize(DataMessageType.CommandsList, "payload");
    }
}
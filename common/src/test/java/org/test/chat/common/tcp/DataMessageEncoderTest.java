package org.test.chat.common.tcp;

import io.netty.buffer.ByteBuf;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.test.chat.common.data_message.DataMessage;
import org.test.chat.common.data_message.DataMessageSerializerDeserializer;

import java.nio.charset.Charset;

public class DataMessageEncoderTest {

    private DataMessageEncoder encoder = new DataMessageEncoder();

    @Before
    public void setUp() {
        encoder.serializerDeserializer = Mockito.mock(DataMessageSerializerDeserializer.class);
    }

    @Test
    public void encodeWithEndingLineBreak() {
        Mockito.doReturn("data message").when(encoder.serializerDeserializer).serialize(Mockito.any(DataMessage.class));
        ByteBuf buf = Mockito.mock(ByteBuf.class);


        encoder.encode(null, new DataMessage(), buf);

        Mockito.verify(buf).writeCharSequence(Mockito.eq("data message\n"), Mockito.any(Charset.class));
    }
}
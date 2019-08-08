package org.test.chat.common.data_message;

import org.junit.Test;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

import static org.junit.Assert.assertEquals;

public class PayloadMessageDataTypeFetcherTest {

    private PayloadMessageDataTypeFetcher fetcher = new PayloadMessageDataTypeFetcher();

    @Test
    public void getDataType() {
        @PayloadMessageType(DataMessageType.CommandExecutionResult)
        class UnitTestPayloadMessage implements PayloadMessage {}


        DataMessageType messageType = fetcher.getDataMessageType(UnitTestPayloadMessage.class);


        assertEquals(DataMessageType.CommandExecutionResult, messageType);
    }

    @Test(expected = RuntimeException.class)
    public void getDataTypeForInvalidType() {
        class UnitTestPayloadMessage implements PayloadMessage {}

        fetcher.getDataMessageType(UnitTestPayloadMessage.class);
    }

}
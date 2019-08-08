package org.test.chat.common.data_message.impl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.data_message.MessageSerializerDeserializer;
import org.test.chat.common.data_message.PayloadMessageDataTypeFetcher;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_client.LoginAccepted;
import org.test.chat.common.message.to_server.ExecuteCommand;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

public class ByAnnotationPayloadMessageHandlerProviderTest {

    private ByAnnotationPayloadMessageHandlerProvider provider = new ByAnnotationPayloadMessageHandlerProvider();
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        provider.messageDeserializer = Mockito.mock(MessageSerializerDeserializer.class);
        provider.dataTypeFetcher = Mockito.mock(PayloadMessageDataTypeFetcher.class);
    }




    @Test
    public void getHandlerForDirectInterfaceImplementation() {
        class UnitTestPayloadMessageHandler implements PayloadMessageHandler<LoginAccepted> {
            @Override
            public void onMessage(LoginAccepted message) {}
        }
        doReturn(DataMessageType.LoginAccepted).when(provider.dataTypeFetcher).getDataMessageType(eq(LoginAccepted.class));

        UnitTestPayloadMessageHandler expectedHandler = new UnitTestPayloadMessageHandler();
        provider.handlers = Collections.singletonList(expectedHandler);


        provider.initMessageHandlersMap();
        PayloadMessageHandler actualHandler = provider.getHandler(DataMessageType.LoginAccepted);


        assertEquals(expectedHandler, actualHandler);
    }


    @Test
    public void getHandlerForExtendsFromAbstractClass() {
        class AbstractUnitTestMessageHandler<MessageType extends PayloadMessage> implements PayloadMessageHandler<MessageType> {
            @Override
            public void onMessage(MessageType message) {}
        }

        class SpecificUnitTestMessageHandler extends AbstractUnitTestMessageHandler<ExecuteCommand> {}
        doReturn(DataMessageType.ExecuteCommand).when(provider.dataTypeFetcher).getDataMessageType(eq(ExecuteCommand.class));
        SpecificUnitTestMessageHandler expectedHandler = new SpecificUnitTestMessageHandler();
        provider.handlers = Collections.singletonList(expectedHandler);

        provider.initMessageHandlersMap();
        PayloadMessageHandler actualHandler = provider.getHandler(DataMessageType.ExecuteCommand);

        assertEquals(expectedHandler, actualHandler);
    }


    @Test
    public void gethandlerForInvalidType() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("LoginAccepted not found");

        provider.getHandler(DataMessageType.LoginAccepted);
    }

}
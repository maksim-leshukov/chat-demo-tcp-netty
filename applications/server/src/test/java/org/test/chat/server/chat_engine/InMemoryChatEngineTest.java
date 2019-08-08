package org.test.chat.server.chat_engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.to_client.CommandExecutionResult;
import org.test.chat.common.message.to_client.LastMessages;
import org.test.chat.common.message.to_client.LoginAccepted;
import org.test.chat.server.chat_engine.clients.ClientsStorage;
import org.test.chat.server.chat_engine.messages.ChatMessage;
import org.test.chat.server.chat_engine.messages.MessagesStorage;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class InMemoryChatEngineTest {

    private InMemoryChatEngine engine = new InMemoryChatEngine();

    @Before
    public void setUp() {
        engine.clientsStorage = mock(ClientsStorage.class);
        engine.messageWriter = mock(ClientWriter.class);
        engine.messagesStorage = mock(MessagesStorage.class);
    }

    ArgumentCaptor<PayloadMessage> payloadMessageCaptor = ArgumentCaptor.forClass(PayloadMessage.class);

    @Test
    public void loginAsSuccess() {
        when(engine.clientsStorage.tryAddAs(eq("login1"), any(ClientContext.class), any(BiConsumer.class))).then(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            BiConsumer<String, ClientContext> onSuccess = (BiConsumer) arguments[2];
            onSuccess.accept((String) arguments[0], (ClientContext) arguments[1]);
            return true;
        });
        when(engine.messagesStorage.getMessages(any(Function.class))).then(invocationOnMock -> {
            Function mapper = (Function) invocationOnMock.getArguments()[0];
            return Collections.singletonList(new ChatMessage()).stream().map(mapper).collect(Collectors.toList());
        });


        ClientContext expectedContext = new ClientContext();
        engine.loginAs("login1", expectedContext);


        verify(engine.clientsStorage).tryAddAs(Mockito.eq("login1"), any(ClientContext.class), any(BiConsumer.class));

        verify(engine.messageWriter, times(2)).write(eq(expectedContext), payloadMessageCaptor.capture());
        List<PayloadMessage> payloadMessages = payloadMessageCaptor.getAllValues();
        assertEquals(2, payloadMessages.size());
        assertEquals(LoginAccepted.class, payloadMessages.get(0).getClass());
        assertEquals(LastMessages.class, payloadMessages.get(1).getClass());
    }


    @Test
    public void loginAsFailed() {
        doReturn(false).when(engine.clientsStorage).tryAddAs(anyString(), anyObject(), anyObject());

        engine.loginAs("login2", new ClientContext());

        verify(engine.messageWriter).write(any(ClientContext.class), any(CommandExecutionResult.class));
    }


    @Test
    public void createMessage() {
        when(engine.messagesStorage.addMessage(anyString(), anyString())).then(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setAuthor((String) arguments[0]);
            chatMessage.setMessage((String) arguments[1]);
            return chatMessage;
        });
        doReturn(Collections.singleton(new ClientContext())).when(engine.clientsStorage).getClients();

        engine.createMessage("user3", "msg1");

        verify(engine.messagesStorage).addMessage("user3", "msg1");
    }
}
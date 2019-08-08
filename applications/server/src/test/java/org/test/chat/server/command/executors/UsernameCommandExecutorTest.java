package org.test.chat.server.command.executors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.to_client.CommandExecutionResult;
import org.test.chat.server.chat_engine.ChatEngine;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;

import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UsernameCommandExecutorTest {

    private UsernameCommandExecutor executor = new UsernameCommandExecutor();


    @Before
    public void setUp() {
        executor.clientWriter = mock(ClientWriter.class);
        executor.chatEngine = mock(ChatEngine.class);
    }


    @Test
    public void showCurrentUsername() {
        ClientContext expectedContext = new ClientContext("login3");
        executor.execute(expectedContext, Collections.emptyList());


        ArgumentCaptor<PayloadMessage> payloadMessageCaptor = ArgumentCaptor.forClass(PayloadMessage.class);
        verify(executor.clientWriter).write(eq(expectedContext), payloadMessageCaptor.capture());

        CommandExecutionResult commandResult = (CommandExecutionResult) payloadMessageCaptor.getValue();
        assertTrue(commandResult.getText().contains("login3"));
    }


    @Test
    public void changeUsername() {
        ClientContext currentContext = new ClientContext();
        executor.execute(currentContext, Collections.singletonList("new_login"));

        InOrder inOrder = Mockito.inOrder(executor.chatEngine);
        inOrder.verify(executor.chatEngine).logout(eq(currentContext));
        inOrder.verify(executor.chatEngine).loginAs(eq("new_login"), eq(currentContext));
    }

}
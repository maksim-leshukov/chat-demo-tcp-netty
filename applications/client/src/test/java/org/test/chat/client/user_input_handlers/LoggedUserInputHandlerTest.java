package org.test.chat.client.user_input_handlers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.test.chat.client.gui.UserDialogService;
import org.test.chat.client.transport.ServerDataMessageWriter;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.to_server.ExecuteCommand;
import org.test.chat.common.message.to_server.SendMessage;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoggedUserInputHandlerTest {

    private LoggedUserInputHandler handler = new LoggedUserInputHandler();

    ArgumentCaptor<PayloadMessage> payloadMessageCaptor = ArgumentCaptor.forClass(PayloadMessage.class);

    @Before
    public void setUp() {
        handler.messageWriter = mock(ServerDataMessageWriter.class);
        handler.userDialogService = mock(UserDialogService.class);
    }

    @Test
    public void onInvalidCommand() {
        handler.onMessage("/");

        ArgumentCaptor<String> printCaptor = ArgumentCaptor.forClass(String.class);
        verify(handler.userDialogService).print(printCaptor.capture());

        Assert.assertTrue(printCaptor.getValue().contains("Invalid"));
    }

    @Test
    public void onSendMessage() {
        handler.onMessage("Hello");

        verify(handler.messageWriter).write(payloadMessageCaptor.capture());

        assertEquals("Hello", ((SendMessage) payloadMessageCaptor.getValue()).getText());
    }

    @Test
    public void onSendEmptyMessage() {
        handler.onMessage("");

        Mockito.verifyZeroInteractions(handler.messageWriter);
    }

    @Test
    public void onExecuteCommandWithoutParameters() {
        handler.onMessage("/help");

        verify(handler.messageWriter).write(payloadMessageCaptor.capture());

        ExecuteCommand command = (ExecuteCommand) payloadMessageCaptor.getValue();
        assertEquals("help", command.getCommandName());
    }

    @Test
    public void onExecuteCommandWithParameters() {
        handler.onMessage("/command1 arg1 arg2");

        verify(handler.messageWriter).write(payloadMessageCaptor.capture());
        ExecuteCommand command = (ExecuteCommand) payloadMessageCaptor.getValue();

        assertEquals("command1", command.getCommandName());
        assertEquals(Arrays.asList("arg1", "arg2"), command.getParameters());
    }

}
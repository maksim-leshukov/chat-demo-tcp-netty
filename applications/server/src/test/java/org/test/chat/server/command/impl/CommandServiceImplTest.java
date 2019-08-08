package org.test.chat.server.command.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.test.chat.common.message.to_client.CommandExecutionResult;
import org.test.chat.server.command.CommandExecutor;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class CommandServiceImplTest {

    private CommandServiceImpl commandService = new CommandServiceImpl();

    @Before
    public void setUp() throws Exception {
        commandService.clientWriter = mock(ClientWriter.class);
    }

    @Test
    public void onCommand() {
        CommandExecutor executor = mock(CommandExecutor.class);
        doReturn("command1").when(executor).getCommandName();

        commandService.commandExecutors = Collections.singletonList(executor);

        List<String> parameters = Arrays.asList("param1", "param2");
        commandService.initCommandNameMap();
        commandService.onCommand(null, "command1", parameters);


        verify(executor).execute(anyObject(), eq(parameters));
    }

    @Test(expected = RuntimeException.class)
    public void onTwoExecutorForOneCommand() {
        CommandExecutor executor1 = mock(CommandExecutor.class);
        doReturn("command1").when(executor1).getCommandName();

        CommandExecutor executor2 = mock(CommandExecutor.class);
        doReturn("command1").when(executor2).getCommandName();

        commandService.commandExecutors = Arrays.asList(executor1, executor2);
        commandService.initCommandNameMap();
    }

    @Test
    public void onUnknownCommand() {
        ClientContext currentUserContext  = new ClientContext();
        commandService.onCommand(currentUserContext, "command1", null);

        ArgumentCaptor<CommandExecutionResult> messageCaptor = ArgumentCaptor.forClass(CommandExecutionResult.class);
        verify(commandService.clientWriter).write(eq(currentUserContext), messageCaptor.capture());

        Assert.assertTrue(messageCaptor.getValue().getText().contains("Unknown command"));
    }
}
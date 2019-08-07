package org.test.chat.server.client_message_handler;

import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_server.ExecuteCommand;
import org.test.chat.server.transport.ClientContextStorage;
import org.test.chat.server.command.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExecuteCommandHandler implements PayloadMessageHandler<ExecuteCommand> {

    @Autowired
    private CommandService commandService;
    @Autowired
    private ClientContextStorage contextStorage;

    @Override
    public void onMessage(ExecuteCommand message) {
        String commandName = message.getCommandName();
        List<String> parameters = message.getParameters();

        commandService.onCommand(contextStorage.get(), commandName, parameters);
    }
}

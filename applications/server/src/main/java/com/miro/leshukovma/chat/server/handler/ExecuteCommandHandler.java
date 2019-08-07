package com.miro.leshukovma.chat.server.handler;

import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_server.ExecuteCommand;
import com.miro.leshukovma.chat.server.client.ClientContextStorage;
import com.miro.leshukovma.chat.server.command.CommandService;
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

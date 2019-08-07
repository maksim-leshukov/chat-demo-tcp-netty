package com.miro.leshukovma.chat.server.command.executors;

import com.miro.leshukovma.chat.common.message.to_client.CommandExecutionResult;
import com.miro.leshukovma.chat.server.transport.ClientContext;
import com.miro.leshukovma.chat.server.transport.ClientWriter;
import com.miro.leshukovma.chat.server.command.CommandExecutor;
import com.miro.leshukovma.chat.server.command.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpCommandExecutor implements CommandExecutor {

    @Autowired
    private CommandService commandService;
    @Autowired
    private ClientWriter clientWriter;

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public String getCommandDescription() {
        return "Show list of all available commands";
    }

    @Override
    public void execute(ClientContext clientContext, List<String> parameters) {
        String commands = commandService.getCommands().stream()
                .map(executor -> "/" + executor.getCommandName() + " - " + executor.getCommandDescription())
                .collect(Collectors.joining("\n"));

        clientWriter.write(clientContext, new CommandExecutionResult("Available commands:\n" + commands));
    }
}

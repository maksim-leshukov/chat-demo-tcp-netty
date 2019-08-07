package org.test.chat.server.command.impl;

import org.test.chat.common.message.to_client.CommandExecutionResult;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;
import org.test.chat.server.command.CommandExecutor;
import org.test.chat.server.command.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    private List<CommandExecutor> commandExecutors;
    @Autowired
    private ClientWriter clientWriter;

    private final Map<String, CommandExecutor> commandName2executor = new HashMap<>();

    @PostConstruct
    public void initCommandNameMap() {
        for (CommandExecutor executor : commandExecutors) {
            String commandName = executor.getCommandName();
            CommandExecutor oldExecutor = commandName2executor.put(commandName, executor);
            if (oldExecutor != null) {
                throw new RuntimeException("For command '" + commandName + "' exist two executors: " + executor + ", " + oldExecutor);
            }
        }

    }


    @Override
    public void onCommand(ClientContext clientContext, String commandName, List<String> parameters) {
        CommandExecutor executor = commandName2executor.get(commandName);
        if (executor == null) {
            clientWriter.write(clientContext, new CommandExecutionResult("Unknown command '" + commandName + "'. You can show commands list by command '/help'."));
        } else {
            executor.execute(clientContext, parameters);
        }
    }


    @Override
    public List<CommandExecutor> getCommands() {
        return commandExecutors;
    }

}

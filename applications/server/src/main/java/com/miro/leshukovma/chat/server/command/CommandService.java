package com.miro.leshukovma.chat.server.command;

import com.miro.leshukovma.chat.common.message.to_client.CommandExecutionResult;
import com.miro.leshukovma.chat.server.client.ClientContext;
import com.miro.leshukovma.chat.server.client.ClientWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommandService {

    @Autowired
    private List<CommandExecutor> commandExecutors;
    @Autowired
    private ClientWriter clientWriter;

    private Map<String, CommandExecutor> commandName2executor = new HashMap<>();

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

    public void onCommand(ClientContext clientContext, String commandName, List<String> parameters) {
        CommandExecutor executor = commandName2executor.get(commandName);
        if (executor == null) {
            clientWriter.printMessage(clientContext, new CommandExecutionResult("Unknown command '" + commandName + "'. You can show commands list by command '/help'."));
            return;
        }
        executor.execute(clientContext, parameters);
    }

    public List<CommandExecutor> getCommands() {
        return commandExecutors;
    }

}

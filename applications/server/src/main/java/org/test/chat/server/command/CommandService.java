package org.test.chat.server.command;

import org.test.chat.server.transport.ClientContext;

import java.util.List;

public interface CommandService {

    void onCommand(ClientContext clientContext, String commandName, List<String> parameters);

    List<CommandExecutor> getCommands();

}

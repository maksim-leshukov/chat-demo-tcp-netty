package com.miro.leshukovma.chat.server.command;

import com.miro.leshukovma.chat.server.transport.ClientContext;

import java.util.List;

public interface CommandService {

    void onCommand(ClientContext clientContext, String commandName, List<String> parameters);

    List<CommandExecutor> getCommands();

}

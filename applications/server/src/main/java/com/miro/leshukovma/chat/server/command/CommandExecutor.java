package com.miro.leshukovma.chat.server.command;

import com.miro.leshukovma.chat.server.client.ClientContext;

import java.util.List;

public interface CommandExecutor {
    String getCommandName();

    String getCommandDescription();

    void execute(ClientContext clientContext, List<String> parameters);
}

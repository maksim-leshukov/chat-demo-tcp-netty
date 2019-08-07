package org.test.chat.server.command;

import org.test.chat.server.transport.ClientContext;

import java.util.List;

public interface CommandExecutor {
    String getCommandName();

    String getCommandDescription();

    void execute(ClientContext clientContext, List<String> parameters);
}

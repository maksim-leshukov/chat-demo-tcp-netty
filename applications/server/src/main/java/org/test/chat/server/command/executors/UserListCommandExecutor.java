package org.test.chat.server.command.executors;

import org.test.chat.common.message.to_client.CommandExecutionResult;
import org.test.chat.server.chat_engine.clients.ClientsStorage;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;
import org.test.chat.server.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserListCommandExecutor implements CommandExecutor {

    @Autowired
    private ClientsStorage clientsStorage;
    @Autowired
    private ClientWriter messageWriter;

    @Override
    public String getCommandName() {
        return "userlist";
    }

    @Override
    public String getCommandDescription() {
        return "Show online users count and list of username";
    }

    @Override
    public void execute(ClientContext clientContext, List<String> parameters) {
        Collection<String> usernames = clientsStorage.getClientLogins();
        String usernamesBlock = usernames.stream()
                .map(username -> "- " + username)
                .collect(Collectors.joining("\n"));
        messageWriter.write(new CommandExecutionResult(usernames.size() + " user(s) online:\n" + usernamesBlock));
    }
}

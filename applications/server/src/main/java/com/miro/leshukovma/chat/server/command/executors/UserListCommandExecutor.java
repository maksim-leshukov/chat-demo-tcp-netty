package com.miro.leshukovma.chat.server.command.executors;

import com.miro.leshukovma.chat.common.message.to_client.CommandExecutionResult;
import com.miro.leshukovma.chat.server.client.ClientContext;
import com.miro.leshukovma.chat.server.client.ServerDataMessageWriter;
import com.miro.leshukovma.chat.server.command.CommandExecutor;
import com.miro.leshukovma.chat.server.engine.ClientsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserListCommandExecutor implements CommandExecutor {

    @Autowired
    private ClientsHolder clientsHolder;
    @Autowired
    private ServerDataMessageWriter messageWriter;

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
        Collection<String> usernames = clientsHolder.getUserLogins();
        String usernamesBlock = usernames.stream()
                .map(username -> "- " + username)
                .collect(Collectors.joining("\n"));
        messageWriter.write(new CommandExecutionResult(usernames.size() + " user(s) online:\n" + usernamesBlock));
    }
}

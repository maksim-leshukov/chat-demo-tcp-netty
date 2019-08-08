package org.test.chat.server.command.executors;

import org.test.chat.common.message.to_client.CommandExecutionResult;
import org.test.chat.server.chat_engine.ChatEngine;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;
import org.test.chat.server.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsernameCommandExecutor implements CommandExecutor {

    @Autowired
    protected ClientWriter clientWriter;
    @Autowired
    protected ChatEngine chatEngine;

    @Override
    public String getCommandName() {
        return "username";
    }

    @Override
    public String getCommandDescription() {
        return "Show or change username (show username: '/username', change username: '/username new_username')";
    }

    @Override
    public void execute(ClientContext clientContext, List<String> parameters) {
        if (parameters.isEmpty()) {
            clientWriter.write(clientContext, new CommandExecutionResult("Current username: '" + clientContext.getLogin() + "'"));
        } else {
            String login = parameters.get(0).trim();
            chatEngine.logout(clientContext);
            chatEngine.loginAs(login, clientContext);
        }
    }
}

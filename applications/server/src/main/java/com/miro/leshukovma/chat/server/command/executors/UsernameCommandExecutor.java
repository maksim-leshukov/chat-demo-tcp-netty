package com.miro.leshukovma.chat.server.command.executors;

import com.miro.leshukovma.chat.common.message.to_client.CommandExecutionResult;
import com.miro.leshukovma.chat.server.chat_engine.ChatEngine;
import com.miro.leshukovma.chat.server.transport.ClientContext;
import com.miro.leshukovma.chat.server.transport.ClientWriter;
import com.miro.leshukovma.chat.server.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsernameCommandExecutor implements CommandExecutor {

    @Autowired
    private ClientWriter clientWriter;
    @Autowired
    private ChatEngine chatEngine;

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

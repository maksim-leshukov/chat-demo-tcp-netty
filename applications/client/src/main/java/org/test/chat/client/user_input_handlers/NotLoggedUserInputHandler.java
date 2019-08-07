package org.test.chat.client.user_input_handlers;

import org.test.chat.client.gui.UserDialogService;
import org.test.chat.client.transport.ServerDataMessageWriter;
import org.test.chat.common.message.to_server.LoginAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotLoggedUserInputHandler implements UserInputHandler {

    @Autowired
    private ServerDataMessageWriter messageWriter;
    @Autowired
    private UserDialogService userDialogService;

    public void onMessage(String userInput) {
        String username = userInput.trim();

        userDialogService.print("You tried login as '" + username + "'");
        messageWriter.write(new LoginAs(username));
    }

}

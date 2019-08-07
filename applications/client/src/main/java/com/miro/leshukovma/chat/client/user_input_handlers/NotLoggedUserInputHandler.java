package com.miro.leshukovma.chat.client.user_input_handlers;

import com.miro.leshukovma.chat.client.gui.UserDialogService;
import com.miro.leshukovma.chat.client.transport.ServerDataMessageWriter;
import com.miro.leshukovma.chat.common.message.to_server.LoginAs;
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

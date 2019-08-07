package org.test.chat.client.server_message_handler;

import org.test.chat.client.gui.UserDialogService;
import org.test.chat.client.gui.UserInputHandlerKeeper;
import org.test.chat.client.user_input_handlers.LoggedUserInputHandler;
import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_client.LoginAccepted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginAcceptedHandler implements PayloadMessageHandler<LoginAccepted> {


    @Autowired
    private UserInputHandlerKeeper userInputHandlerKeeper;
    @Autowired
    private LoggedUserInputHandler loggedUserInputHandler;
    @Autowired
    private UserDialogService userDialogService;

    public void onMessage(LoginAccepted message) {
        userInputHandlerKeeper.setUserInputHandler(loggedUserInputHandler);
        userDialogService.print("Login successful");
    }
}

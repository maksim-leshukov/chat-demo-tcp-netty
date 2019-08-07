package com.miro.leshukovma.chat.client.handler;

import com.miro.leshukovma.chat.client.gui.UserDialogService;
import com.miro.leshukovma.chat.client.gui.UserInputDispatcher;
import com.miro.leshukovma.chat.client.gui.input_handlers.LoggedUserInputHandler;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_client.LoginAccepted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginAcceptedHandler implements PayloadMessageHandler<LoginAccepted> {


    @Autowired
    private UserInputDispatcher userInputDispatcher;
    @Autowired
    private LoggedUserInputHandler loggedUserInputHandler;
    @Autowired
    private UserDialogService userDialogService;

    public void onMessage(LoginAccepted message) {
        userInputDispatcher.setUserInputHandler(loggedUserInputHandler);
        userDialogService.print("Login successful");
    }
}

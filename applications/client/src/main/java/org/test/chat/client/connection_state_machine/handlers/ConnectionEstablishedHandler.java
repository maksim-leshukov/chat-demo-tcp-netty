package org.test.chat.client.connection_state_machine.handlers;

import org.test.chat.client.gui.UserDialogService;
import org.test.chat.client.gui.UserInputHandlerKeeper;
import org.test.chat.client.user_input_handlers.NotLoggedUserInputHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@Slf4j
@WithStateMachine
public class ConnectionEstablishedHandler {

    @Autowired
    private UserInputHandlerKeeper userInputHandlerKeeper;
    @Autowired
    private NotLoggedUserInputHandler notLoggedUserInputHandler;

    @Autowired
    private UserDialogService userDialogService;

    @OnTransition(target = "Connected")
    public void onConnectionEstablished() {
        userInputHandlerKeeper.setUserInputHandler(notLoggedUserInputHandler);

        userDialogService.print("Please choose username for chat session");
    }

}

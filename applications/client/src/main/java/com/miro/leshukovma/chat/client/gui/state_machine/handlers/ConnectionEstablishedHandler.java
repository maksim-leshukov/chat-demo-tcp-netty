package com.miro.leshukovma.chat.client.gui.state_machine.handlers;

import com.miro.leshukovma.chat.client.gui.UserDialogService;
import com.miro.leshukovma.chat.client.gui.UserInputDispatcher;
import com.miro.leshukovma.chat.client.gui.input_handlers.NotLoggedUserInputHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@Slf4j
@WithStateMachine
public class ConnectionEstablishedHandler {

    @Autowired
    private UserInputDispatcher userInputDispatcher;
    @Autowired
    private NotLoggedUserInputHandler notLoggedUserInputHandler;

    @Autowired
    private UserDialogService userDialogService;

    @OnTransition(target = "Connected")
    public void onConnectionEstablished() {
        log.info("On connected by annotation");

        userInputDispatcher.setUserInputHandler(notLoggedUserInputHandler);

        userDialogService.print("Please choose username for chat session");
    }

}

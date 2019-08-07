package com.miro.leshukovma.chat.client.gui;

import com.miro.leshukovma.chat.client.user_input_handlers.UserInputHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInputHandlerKeeper {

    @Setter
    private volatile UserInputHandler userInputHandler;

    public void dispatch(String userInput) {
        if (userInputHandler != null) {
            userInputHandler.onMessage(userInput);
        } else {
            log.warn("Handler is null for input '{}'", userInput);
        }
    }

}

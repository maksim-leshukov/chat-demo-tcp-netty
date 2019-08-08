package org.test.chat.client.gui;

import org.test.chat.client.user_input_handlers.UserInputHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInputHandlerKeeper {

    @Setter
    private volatile UserInputHandler userInputHandler;

    public void dispatch(String userInput) {
        userInputHandler.onMessage(userInput);
    }

}

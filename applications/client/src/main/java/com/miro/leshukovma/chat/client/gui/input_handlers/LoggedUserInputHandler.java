package com.miro.leshukovma.chat.client.gui.input_handlers;

import com.miro.leshukovma.chat.client.ClientDataMessageWriter;
import com.miro.leshukovma.chat.client.gui.UserDialogService;
import com.miro.leshukovma.chat.common.message.to_server.ExecuteCommand;
import com.miro.leshukovma.chat.common.message.to_server.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class LoggedUserInputHandler implements UserInputHandler {


    @Autowired
    private ClientDataMessageWriter messageWriter;
    @Autowired
    private UserDialogService userDialogService;

    public void onMessage(String userInput) {
        log.info("User input: '{}'", userInput);

        if (StringUtils.isNotEmpty(userInput)) {
            boolean isCommand = userInput.startsWith("/");
            if (isCommand) {
                if (userInput.length() <= 1) {
                    userDialogService.print("Invalid command: '" + userInput + "'");
                } else {
                    int spaceIndex = userInput.indexOf(" ");
                    if (spaceIndex == -1) {
                        spaceIndex = userInput.length();
                    }
                    String commandName = userInput.substring(1, spaceIndex);
                    String commandParameters = userInput.substring(spaceIndex);
                    List<String> parameters = StringUtils.isEmpty(commandParameters)
                            ? Collections.EMPTY_LIST
                            : Arrays.asList(commandParameters.split("\'s+"));
                    messageWriter.write(new ExecuteCommand(commandName, parameters));
                }
            } else {
                messageWriter.write(new SendMessage(userInput));
            }
        }
    }
}

package org.test.chat.client.user_input_handlers;

import org.test.chat.client.gui.UserDialogService;
import org.test.chat.client.transport.ServerDataMessageWriter;
import org.test.chat.common.message.to_server.ExecuteCommand;
import org.test.chat.common.message.to_server.SendMessage;
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
    protected ServerDataMessageWriter messageWriter;
    @Autowired
    protected UserDialogService userDialogService;

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
                    String commandName = userInput.substring(1, spaceIndex).trim();
                    String commandParameters = userInput.substring(spaceIndex).trim();
                    List<String> parameters = StringUtils.isEmpty(commandParameters)
                            ? Collections.EMPTY_LIST
                            : Arrays.asList(commandParameters.split("\\s+"));
                    messageWriter.write(new ExecuteCommand(commandName, parameters));
                }
            } else {
                messageWriter.write(new SendMessage(userInput));
            }
        }
    }
}

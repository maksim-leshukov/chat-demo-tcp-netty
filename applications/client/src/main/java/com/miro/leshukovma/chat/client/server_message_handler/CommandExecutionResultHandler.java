package com.miro.leshukovma.chat.client.server_message_handler;

import com.miro.leshukovma.chat.client.gui.UserDialogService;
import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_client.CommandExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutionResultHandler implements PayloadMessageHandler<CommandExecutionResult> {

    @Autowired
    private UserDialogService dialogService;

    public void onMessage(CommandExecutionResult message) {
        dialogService.print(message.getText());
    }
}

package com.miro.leshukovma.chat.server.client_message_handler;

import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_server.LoginAs;
import com.miro.leshukovma.chat.server.chat_engine.ChatEngine;
import com.miro.leshukovma.chat.server.transport.ClientContext;
import com.miro.leshukovma.chat.server.transport.ClientContextStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginAsMessageHandler implements PayloadMessageHandler<LoginAs> {

    @Autowired
    private ClientContextStorage contextStorage;
    @Autowired
    private ChatEngine chatEngine;

    public void onMessage(LoginAs message) {
        String login = message.getLogin();

        ClientContext clientContext = contextStorage.get();
        chatEngine.loginAs(login, clientContext);
    }

}

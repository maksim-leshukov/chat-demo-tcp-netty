package org.test.chat.server.client_message_handler;

import org.test.chat.common.message.PayloadMessageHandler;
import org.test.chat.common.message.to_server.LoginAs;
import org.test.chat.server.chat_engine.ChatEngine;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientContextStorage;
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

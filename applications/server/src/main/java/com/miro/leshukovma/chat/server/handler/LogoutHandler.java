package com.miro.leshukovma.chat.server.handler;

import com.miro.leshukovma.chat.common.message.PayloadMessageHandler;
import com.miro.leshukovma.chat.common.message.to_server.Logout;
import com.miro.leshukovma.chat.server.client.ClientContext;
import com.miro.leshukovma.chat.server.client.ClientContextStorage;
import com.miro.leshukovma.chat.server.engine.ChatEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutHandler implements PayloadMessageHandler<Logout> {

    @Autowired
    private ChatEngine chatEngine;
    @Autowired
    private ClientContextStorage contextStorage;

    @Override
    public void onMessage(Logout message) {
        ClientContext client = contextStorage.get();
        chatEngine.onUserLogout(client);
        client.getTransportContext().close();
    }

}

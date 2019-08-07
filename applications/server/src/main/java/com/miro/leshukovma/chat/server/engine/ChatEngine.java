package com.miro.leshukovma.chat.server.engine;

import com.miro.leshukovma.chat.common.message.to_client.*;
import com.miro.leshukovma.chat.server.client.ClientContext;
import com.miro.leshukovma.chat.server.client.ServerDataMessageWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class ChatEngine {

    @Autowired
    private MessagesHolder messagesHolder;
    @Autowired
    private ClientsHolder clientsHolder;
    @Autowired
    private ServerDataMessageWriter messageWriter;


    public void onUserLogin(String login, ClientContext client) {
        boolean successful = clientsHolder.addAs(login, client, this::onSuccessLogin);
        if (!successful) {
            messageWriter.write(client, new CommandExecutionResult("Login '" + login + "' not valid"));
        }
    }

    private void onSuccessLogin(String login, ClientContext client) {
        client.setLogin(login);
        log.debug("Logged as: '{}'", login);
        messageWriter.write(client, new LoginAccepted());

        List<ChatMessageDto> lastMessages = messagesHolder.getMessages(chatMessage -> {
            ChatMessageDto chatMessageDto = new ChatMessageDto();
            mapMessage(chatMessage, chatMessageDto);
            return chatMessageDto;
        });
        messageWriter.write(client, new LastMessages(lastMessages));
    }

    public void onUserLogout(ClientContext client) {
        String login = client.getLogin();
        log.debug("Logout as: {}", login);

        clientsHolder.remove(login);
        client.setLogin(null);
        messageWriter.write(new CommandExecutionResult("Logout by '" + login + "' is successful"));
    }

    public void onUserMessage(String senderUsername, String message) {
        log.debug("Send message as: {}", senderUsername);
        ChatMessage chatMessage = messagesHolder.addMessage(senderUsername, message);

        NewMessage payloadMessage = new NewMessage();
        mapMessage(chatMessage, payloadMessage);

        Collection<ClientContext> clients = clientsHolder.getClients();
        for (ClientContext clientToSendMessage : clients) {
            messageWriter.write(clientToSendMessage, payloadMessage);
        }

    }

    private void mapMessage(ChatMessage chatMessage, ChatMessageDto payloadMessage) {
        payloadMessage.setDate(chatMessage.getDate());
        payloadMessage.setAuthor(chatMessage.getAuthor());
        payloadMessage.setMessage(chatMessage.getMessage());
    }

}

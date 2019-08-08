package org.test.chat.server.chat_engine;

import org.test.chat.common.message.to_client.*;
import org.test.chat.server.chat_engine.clients.ClientsStorage;
import org.test.chat.server.chat_engine.messages.ChatMessage;
import org.test.chat.server.chat_engine.messages.MessagesStorage;
import org.test.chat.server.transport.ClientContext;
import org.test.chat.server.transport.ClientWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class InMemoryChatEngine implements ChatEngine {

    @Autowired
    protected MessagesStorage messagesStorage;
    @Autowired
    protected ClientsStorage clientsStorage;
    @Autowired
    protected ClientWriter messageWriter;

    @Override
    public void loginAs(String login, ClientContext client) {
        boolean successful = clientsStorage.tryAddAs(login, client, this::onSuccessLogin);
        if (!successful) {
            messageWriter.write(client, new CommandExecutionResult("Login '" + login + "' not valid"));
        }
    }

    private void onSuccessLogin(String login, ClientContext client) {
        client.setLogin(login);
        log.debug("Logged as: '{}'", login);
        messageWriter.write(client, new LoginAccepted());

        List<ChatMessageDto> lastMessages = messagesStorage.getMessages(chatMessage -> {
            ChatMessageDto chatMessageDto = new ChatMessageDto();
            mapMessage(chatMessage, chatMessageDto);
            return chatMessageDto;
        });
        messageWriter.write(client, new LastMessages(lastMessages));
    }


    @Override
    public void logout(ClientContext client) {
        String login = client.getLogin();
        log.debug("Logout as: {}", login);

        clientsStorage.remove(login);
        client.setLogin(null);
        messageWriter.write(new CommandExecutionResult("Logout by '" + login + "' is successful"));
    }


    @Override
    public void createMessage(String senderUsername, String message) {
        log.debug("Send message as: {}", senderUsername);
        ChatMessage chatMessage = messagesStorage.addMessage(senderUsername, message);

        NewMessage payloadMessage = new NewMessage();
        mapMessage(chatMessage, payloadMessage);

        Collection<ClientContext> clients = clientsStorage.getClients();
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

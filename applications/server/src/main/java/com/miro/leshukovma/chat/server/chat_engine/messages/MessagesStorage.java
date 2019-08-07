package com.miro.leshukovma.chat.server.chat_engine.messages;

import java.util.List;
import java.util.function.Function;

public interface MessagesStorage {

    ChatMessage addMessage(String senderUsername, String message);

    <ResultMessageType> List<ResultMessageType> getMessages(Function<ChatMessage, ResultMessageType> mapper);

}

package org.test.chat.server.chat_engine.messages;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InMemoryMessagesStorage implements MessagesStorage {

    @Value("${miro.chat.server.message.storage_limit:100}")
    private int messagesStorageLimit;

    private CircularFifoQueue<ChatMessage> messages;
    private ConcurrentLinkedQueue<ChatMessage> msgs;

    private final AtomicLong atomicAddeMessagesCount = new AtomicLong();

    @PostConstruct
    public void initMessagesStorage() {
        messages = new CircularFifoQueue<>(messagesStorageLimit);
    }

    @Override
    public synchronized ChatMessage addMessage(String senderUsername, String message) {

        ChatMessage messagePojo = new ChatMessage();
        messagePojo.setDate(LocalDateTime.now());
        messagePojo.setAuthor(senderUsername);
        messagePojo.setMessage(message);

        messages.add(messagePojo);

        long addedMessagesCount = atomicAddeMessagesCount.incrementAndGet();
        if (addedMessagesCount % 1000 == 0) {
            log.info("{} messages added", addedMessagesCount);
        }

        return messagePojo;
    }


    @Override
    public synchronized
    <ResultMessageType> List<ResultMessageType> getMessages(Function<ChatMessage, ResultMessageType> mapper) {

        return messages.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}

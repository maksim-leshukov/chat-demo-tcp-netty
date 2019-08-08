package org.test.chat.server.chat_engine.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    private LocalDateTime date;
    private String author;
    private String message;
}

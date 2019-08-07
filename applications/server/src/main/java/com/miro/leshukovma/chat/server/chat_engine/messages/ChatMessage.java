package com.miro.leshukovma.chat.server.chat_engine.messages;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private LocalDateTime date;
    private String author;
    private String message;
}

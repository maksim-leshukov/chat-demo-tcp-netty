package com.miro.leshukovma.chat.server.engine;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private LocalDateTime date;
    private String author;
    private String message;
}

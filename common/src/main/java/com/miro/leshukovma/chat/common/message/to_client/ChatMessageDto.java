package com.miro.leshukovma.chat.common.message.to_client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private LocalDateTime date;
    private String author;
    private String message;
}

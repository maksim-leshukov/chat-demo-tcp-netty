package org.test.chat.common.message.to_client;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private LocalDateTime date;
    private String author;
    private String message;
}

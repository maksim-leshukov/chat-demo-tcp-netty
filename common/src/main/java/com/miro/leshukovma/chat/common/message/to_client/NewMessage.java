package com.miro.leshukovma.chat.common.message.to_client;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@PayloadMessageType(DataMessageType.NewMessage)
public class NewMessage extends ChatMessageDto implements PayloadMessage {

    public NewMessage(LocalDateTime date, String author, String message) {
        super(date, author, message);
    }

    public NewMessage() {
    }
}

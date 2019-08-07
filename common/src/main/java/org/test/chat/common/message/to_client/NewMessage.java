package org.test.chat.common.message.to_client;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;
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

package org.test.chat.common.message.to_client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@PayloadMessageType(DataMessageType.NewMessage)
public class NewMessage extends ChatMessageDto implements PayloadMessage {

    public NewMessage(LocalDateTime date, String author, String message) {
        super(date, author, message);
    }

}

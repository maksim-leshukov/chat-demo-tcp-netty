package org.test.chat.common.message.to_server;

import lombok.*;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PayloadMessageType(DataMessageType.SendMessage)
public class SendMessage implements PayloadMessage {

    private String text;

}

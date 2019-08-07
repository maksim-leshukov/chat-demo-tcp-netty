package org.test.chat.common.message.to_server;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PayloadMessageType(DataMessageType.SendMessage)
public class SendMessage implements PayloadMessage {

//    private ZonedDateTime sendTime;

    private String text;

}

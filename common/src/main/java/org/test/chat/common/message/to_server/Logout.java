package org.test.chat.common.message.to_server;

import lombok.*;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@PayloadMessageType(DataMessageType.Logout)
public class Logout implements PayloadMessage {
}

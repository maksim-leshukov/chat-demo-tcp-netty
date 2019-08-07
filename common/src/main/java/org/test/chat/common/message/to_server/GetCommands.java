package org.test.chat.common.message.to_server;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;
import lombok.Data;

@Data
@PayloadMessageType(DataMessageType.GetCommands)
public class GetCommands implements PayloadMessage {
}

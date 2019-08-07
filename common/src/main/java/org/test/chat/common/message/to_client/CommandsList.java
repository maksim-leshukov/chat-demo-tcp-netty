package org.test.chat.common.message.to_client;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;
import lombok.Data;

@Data
@PayloadMessageType(DataMessageType.CommandsList)
public class CommandsList implements PayloadMessage {
}

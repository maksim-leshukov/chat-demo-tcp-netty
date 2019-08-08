package org.test.chat.common.message.to_client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

@Getter
@Setter
@ToString
@PayloadMessageType(DataMessageType.CommandsList)
public class CommandsList implements PayloadMessage {
}

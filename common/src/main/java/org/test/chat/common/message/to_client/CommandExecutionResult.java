package org.test.chat.common.message.to_client;

import lombok.*;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PayloadMessageType(DataMessageType.CommandExecutionResult)
public class CommandExecutionResult implements PayloadMessage {
    private String text;
}

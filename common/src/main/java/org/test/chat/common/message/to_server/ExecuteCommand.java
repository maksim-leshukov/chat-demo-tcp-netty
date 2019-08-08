package org.test.chat.common.message.to_server;

import lombok.*;
import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PayloadMessageType(DataMessageType.ExecuteCommand)
public class ExecuteCommand implements PayloadMessage {
    private String commandName;
    private List<String> parameters = new ArrayList<>();
}

package org.test.chat.common.message.to_client;

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
@PayloadMessageType(DataMessageType.LastMessages)
public class LastMessages implements PayloadMessage {
    private List<ChatMessageDto> messages = new ArrayList<>();
}

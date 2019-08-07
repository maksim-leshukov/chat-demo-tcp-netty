package org.test.chat.common.message.to_client;

import org.test.chat.common.DataMessageType;
import org.test.chat.common.message.PayloadMessage;
import org.test.chat.common.message.PayloadMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PayloadMessageType(DataMessageType.LastMessages)
public class LastMessages implements PayloadMessage {
    private List<ChatMessageDto> messages = new ArrayList<>();
}

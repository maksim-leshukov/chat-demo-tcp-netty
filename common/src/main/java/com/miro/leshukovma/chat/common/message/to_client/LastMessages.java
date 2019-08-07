package com.miro.leshukovma.chat.common.message.to_client;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
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

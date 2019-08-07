package com.miro.leshukovma.chat.common.message.to_server;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PayloadMessageType(DataMessageType.Logout)
public class Logout implements PayloadMessage {
}

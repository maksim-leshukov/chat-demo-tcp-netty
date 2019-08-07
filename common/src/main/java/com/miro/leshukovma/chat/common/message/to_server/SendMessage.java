package com.miro.leshukovma.chat.common.message.to_server;

import com.miro.leshukovma.chat.common.DataMessageType;
import com.miro.leshukovma.chat.common.message.PayloadMessage;
import com.miro.leshukovma.chat.common.message.PayloadMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PayloadMessageType(DataMessageType.SendMessage)
public class SendMessage implements PayloadMessage {

//    private ZonedDateTime sendTime;

    private String text;

}

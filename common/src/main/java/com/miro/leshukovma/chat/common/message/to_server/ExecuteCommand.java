package com.miro.leshukovma.chat.common.message.to_server;

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
@PayloadMessageType(DataMessageType.ExecuteCommand)
public class ExecuteCommand implements PayloadMessage {
    private String commandName;
    private List<String> parameters = new ArrayList<>();
}

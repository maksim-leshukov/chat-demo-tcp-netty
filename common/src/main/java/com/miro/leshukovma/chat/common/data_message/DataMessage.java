package com.miro.leshukovma.chat.common.data_message;

import com.miro.leshukovma.chat.common.DataMessageType;
import lombok.Data;

@Data
public class DataMessage {

    private DataMessageType type;
    private String payload;

}

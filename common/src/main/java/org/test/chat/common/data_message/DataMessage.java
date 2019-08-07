package org.test.chat.common.data_message;

import org.test.chat.common.DataMessageType;
import lombok.Data;

@Data
public class DataMessage {

    private DataMessageType type;
    private String payload;

}

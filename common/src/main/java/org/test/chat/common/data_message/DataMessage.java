package org.test.chat.common.data_message;

import lombok.*;
import org.test.chat.common.DataMessageType;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataMessage {

    private DataMessageType type;
    private String payload;

}

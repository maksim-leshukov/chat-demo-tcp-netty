package org.test.chat.server.transport;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientContext {
    private volatile String login;
}

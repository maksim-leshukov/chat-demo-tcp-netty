package org.test.chat.server.transport;

import lombok.Data;

@Data
public class ClientContext {
    private volatile String login;
}

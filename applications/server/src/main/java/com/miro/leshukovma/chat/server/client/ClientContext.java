package com.miro.leshukovma.chat.server.client;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

@Data
public class ClientContext {
    private volatile String login;
    private ChannelHandlerContext transportContext;
}

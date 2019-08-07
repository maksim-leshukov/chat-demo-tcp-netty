package com.miro.leshukovma.chat.server.transport.tcp;

import com.miro.leshukovma.chat.server.transport.ClientContext;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

@Data
public class TcpServerClientContext extends ClientContext {
    private ChannelHandlerContext transportContext;
}

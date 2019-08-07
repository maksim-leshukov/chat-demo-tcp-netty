package org.test.chat.server.transport.tcp;

import org.test.chat.server.transport.ClientContext;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

@Data
public class TcpServerClientContext extends ClientContext {
    private ChannelHandlerContext transportContext;
}

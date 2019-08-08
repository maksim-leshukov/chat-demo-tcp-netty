package org.test.chat.server.transport.tcp;

import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.test.chat.server.transport.ClientContext;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TcpServerClientContext extends ClientContext {
    private ChannelHandlerContext transportContext;
}

package org.test.chat.server.transport.tcp;

import io.netty.channel.ChannelHandlerContext;
import org.junit.Test;
import org.test.chat.server.chat_engine.ChatEngine;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TcpChatServerConnectionHandlerTest {

    private TcpChatServerConnectionHandler handler = new TcpChatServerConnectionHandler();

    @Test
    public void exceptionCaughtWhenNotLogged() {
        handler.exceptionCaught(mock(ChannelHandlerContext.class), new RuntimeException());
    }

    @Test
    public void exceptionCaughtWhenLogged() {
        handler.chatEngine = mock(ChatEngine.class);
        handler.currentConnectionClientContext.setLogin("login1");


        handler.exceptionCaught(mock(ChannelHandlerContext.class), new RuntimeException());


        verify(handler.chatEngine).logout(eq(handler.currentConnectionClientContext));
    }
}
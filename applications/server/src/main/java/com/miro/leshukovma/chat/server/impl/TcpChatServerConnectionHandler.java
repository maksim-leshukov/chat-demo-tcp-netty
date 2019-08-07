package com.miro.leshukovma.chat.server.impl;

import com.miro.leshukovma.chat.common.data_message.DataMessage;
import com.miro.leshukovma.chat.common.data_message.DataMessageSerializerDeserializer;
import com.miro.leshukovma.chat.common.data_message.PayloadMessageDispatcher;
import com.miro.leshukovma.chat.server.client.ClientContext;
import com.miro.leshukovma.chat.server.client.ClientContextStorage;
import com.miro.leshukovma.chat.server.engine.ChatEngine;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TcpChatServerConnectionHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private DataMessageSerializerDeserializer serializerDeserializer;
    @Autowired
    private PayloadMessageDispatcher messageDispatcher;
    @Autowired
    private ClientContextStorage clientContextStorage;
    @Autowired
    private ChatEngine chatEngine;

    private volatile ClientContext currentConnectionClientContext = new ClientContext();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Client connected");

        currentConnectionClientContext.setTransportContext(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logoutUser();
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;
            String json = getJsonString(in);
            log.trace("Received message: {}", json);

            DataMessage dataMessage = serializerDeserializer.deserialize(json);

            clientContextStorage.set(currentConnectionClientContext);

            messageDispatcher.dispatch(dataMessage);

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private String getJsonString(ByteBuf in) {
        int bytesCount = in.readableBytes();
        CharSequence charSequence = in.getCharSequence(0, bytesCount, Charset.forName("UTF-8"));
        return charSequence.toString();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Exception caught", cause);
        logoutUser();

        ctx.close();
    }

    private void logoutUser() {
        boolean isLogged = StringUtils.isNotEmpty(currentConnectionClientContext.getLogin());
        if (isLogged) {
            chatEngine.onUserLogout(currentConnectionClientContext);
        }
    }
}

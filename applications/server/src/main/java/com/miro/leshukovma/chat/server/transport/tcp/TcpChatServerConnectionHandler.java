package com.miro.leshukovma.chat.server.transport.tcp;

import com.miro.leshukovma.chat.common.ByteBufUtil;
import com.miro.leshukovma.chat.common.data_message.DataMessage;
import com.miro.leshukovma.chat.common.data_message.DataMessageSerializerDeserializer;
import com.miro.leshukovma.chat.common.data_message.PayloadMessageDispatcher;
import com.miro.leshukovma.chat.server.chat_engine.ChatEngine;
import com.miro.leshukovma.chat.server.transport.ClientContextStorage;
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


    private final TcpServerClientContext currentConnectionClientContext = new TcpServerClientContext();



    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Client connected");
        currentConnectionClientContext.setTransportContext(ctx);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;
            String json = ByteBufUtil.getJsonString(in);
            log.trace("Received message: {}", json);

            DataMessage dataMessage = serializerDeserializer.deserialize(json);

            clientContextStorage.set(currentConnectionClientContext);

            messageDispatcher.dispatch(dataMessage);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logoutUser();
        super.channelInactive(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Exception caught", cause);
        logoutUser();

        ctx.close();
    }


    private void logoutUser() {
        boolean isLogged = StringUtils.isNotEmpty(currentConnectionClientContext.getLogin());
        if (isLogged) {
            chatEngine.logout(currentConnectionClientContext);
        }
    }

}

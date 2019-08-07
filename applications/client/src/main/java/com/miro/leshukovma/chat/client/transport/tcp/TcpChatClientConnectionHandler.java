package com.miro.leshukovma.chat.client.transport.tcp;

import com.miro.leshukovma.chat.client.connection_state_machine.ConnectionStateMachine;
import com.miro.leshukovma.chat.client.connection_state_machine.StateEvent;
import com.miro.leshukovma.chat.common.ByteBufUtil;
import com.miro.leshukovma.chat.common.data_message.DataMessage;
import com.miro.leshukovma.chat.common.data_message.DataMessageSerializerDeserializer;
import com.miro.leshukovma.chat.common.data_message.PayloadMessageDispatcher;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TcpChatClientConnectionHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private DataMessageSerializerDeserializer serializerDeserializer;
    @Autowired
    private PayloadMessageDispatcher messageDispatcher;
    @Autowired
    private TcpServerDataMessageWriter messageWriter;
    @Autowired
    private ConnectionStateMachine clientStateMachine;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("Channel active");

        messageWriter.setCtx(ctx);
        clientStateMachine.toState(StateEvent.ConnectionEstabilishd);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        try {
            ByteBuf in = (ByteBuf) msg;
            String json = ByteBufUtil.getJsonString(in);

            DataMessage dataMessage = serializerDeserializer.deserialize(json);

            messageDispatcher.dispatch(dataMessage);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("Exception caught {}", ctx, cause);
        ctx.close();
    }



    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel inactive");
        super.channelInactive(ctx);
    }

}

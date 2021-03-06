package org.test.chat.client.transport.tcp;

import org.test.chat.common.tcp.DataMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class TcpChatClient {

    @Value("${miro.chat.client.server.host:localhost}")
    private String host;
    @Value("${miro.chat.client.server.port:8000}")
    private int port;

    @Autowired
    private TcpChatClientConnectionHandler clientHandler;
    @Autowired
    private DataMessageEncoder dataMessageEncoder;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @SneakyThrows
    @PostConstruct
    public void start() {

        Bootstrap bootstrap = new Bootstrap()
            .group(workerGroup)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new JsonObjectDecoder(), dataMessageEncoder, clientHandler);
                }
            });


        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

//        channelFuture.channel().closeFuture().sync();
    }

    @PreDestroy
    public void close() {
        workerGroup.shutdownGracefully();
    }
}

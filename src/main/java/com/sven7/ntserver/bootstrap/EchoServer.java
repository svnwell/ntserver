package com.sven7.ntserver.bootstrap;

import java.net.InetSocketAddress;

import com.sven7.ntserver.configure.EchoServerConfigure;
import com.sven7.ntserver.handler.EchoReadHandler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;

@Service
public class EchoServer implements CommandLineRunner {
    private final EchoServerConfigure configure;
    private final EchoReadHandler readHandler;

    public EchoServer(EchoServerConfigure configure, EchoReadHandler readHandler) {
        this.configure = configure;
        this.readHandler = readHandler;
    }

    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup g = new EpollEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(g).channel(EpollServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(this.configure.getProperties().getPort()))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(readHandler);
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            g.shutdownGracefully().sync();
        }
    }
}

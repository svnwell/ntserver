package com.sven7.ntserver.handler;

import java.util.concurrent.TimeUnit;

import com.sven7.ntserver.task.HeartBeatTask;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
@Component
public class EchoReadHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.channel().eventLoop().scheduleAtFixedRate(new HeartBeatTask(ctx), 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.writeAndFlush(msg);
    }
}

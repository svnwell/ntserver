package com.sven7.ntserver.task;

import io.netty.channel.ChannelHandlerContext;

public class HeartBeatTask implements Runnable {
    private final ChannelHandlerContext ctx;

    public HeartBeatTask(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        ctx.writeAndFlush("1");
    }

}

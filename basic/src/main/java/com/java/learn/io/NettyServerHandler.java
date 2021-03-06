package com.java.learn.io;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author hzliuzhujie
 * @date 2021-12-14
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    // RequestHandler requestHandler = new RequestHandler();
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(String.format("客户端信息： %s", channel.remoteAddress()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        String request = (String)msg;
        System.out.println(String.format("客户端发送的消息 %s : %s", channel.remoteAddress(), request));
        String response = ""; /* requestHandler.handle(request);*/
        ctx.write(response);
        ctx.flush();
    }
}

package org.hello.netty.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.hello.netty.RequestData;
import org.hello.netty.ResponseData;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("ClientHandler channelActive");
        RequestData msg = new RequestData();
        msg.setIntVal(123);
        msg.setStringVal("send msg from myspring proj");
        ChannelFuture future = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("ClientHandler channelRead msg {}", (ResponseData) msg);
        ctx.close();
    }
}

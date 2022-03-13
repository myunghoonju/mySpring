package org.hello.netty.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.hello.netty.RequestData;
import org.hello.netty.ResponseData;

@Slf4j
public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestData requestData = (RequestData) msg;
        ResponseData responseData = new ResponseData();

        responseData.setIntVal(requestData.getIntVal() * 2);
        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
        log.info("ProcessingHandler.channelRead RequestData :: {}", requestData);
    }
}

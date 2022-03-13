package org.hello.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.hello.netty.RequestData;
import org.hello.netty.ResponseData;

@Slf4j
public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf tmp;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("SimpleProcessingHandler.handlerAdded");
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("SimpleProcessingHandler.handlerRemoved");
        tmp.release();
        tmp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        tmp.writeBytes(m);
        m.release();

        if (tmp.readableBytes() >= 4) {
            RequestData requestData = new RequestData();
            ResponseData responseData = new ResponseData();

            requestData.setIntVal(tmp.readInt());
            responseData.setIntVal(requestData.getIntVal() * 2);

            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}

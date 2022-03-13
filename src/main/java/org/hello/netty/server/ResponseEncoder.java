package org.hello.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.hello.netty.ResponseData;

public class ResponseEncoder extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntVal());
    }
}

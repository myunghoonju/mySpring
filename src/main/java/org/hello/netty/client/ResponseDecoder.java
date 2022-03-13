package org.hello.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.hello.netty.ResponseData;

import java.util.List;

public class ResponseDecoder extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseData responseData = new ResponseData();
        responseData.setIntVal(in.readInt());
        out.add(responseData);
    }
}

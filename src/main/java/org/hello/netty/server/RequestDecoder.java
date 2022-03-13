package org.hello.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;
import org.hello.netty.RequestData;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("RequestDecoder decode");
        RequestData requestData = new RequestData();
        requestData.setIntVal(in.readInt());
        int strLen = in.readInt();

        requestData.setStringVal(in.readCharSequence(strLen, charset).toString());
        out.add(requestData);
    }
}

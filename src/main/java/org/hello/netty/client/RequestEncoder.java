package org.hello.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.hello.netty.RequestData;

import java.nio.charset.Charset;

public class RequestEncoder extends MessageToByteEncoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntVal());
        out.writeInt(msg.getStringVal().length());
        out.writeCharSequence(msg.getStringVal(), charset);
    }
}

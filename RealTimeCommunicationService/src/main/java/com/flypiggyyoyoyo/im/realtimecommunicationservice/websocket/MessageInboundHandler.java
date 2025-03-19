package com.flypiggyyoyoyo.im.realtimecommunicationservice.websocket;


import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import io.netty.channel.SimpleChannelInboundHandler;

@Slf4j
@ChannelHandler.Sharable
public class MessageInboundHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务端收到了信息{}", msg.text());
    }
}

package com.flypiggyyoyoyo.im.realtimecommunicationservice.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

// 这是一个基于 Netty 框架的自定义通道入站处理器 WebSocketTokenAuthHeader
// 其主要作用是在 WebSocket 握手阶段
// 从 HTTP 请求头中提取用户的 userUuid 和 token 信息
// 并将这些信息存储到 Netty 通道的属性中

@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class WebSocketTokenAuthHeader extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest){
            FullHttpRequest request = (FullHttpRequest) msg;

            String userUuid = Optional.ofNullable(request.headers().get("userUuid")).map(CharSequence::toString).orElse("");
            String token = Optional.ofNullable(request.headers().get("token")).map(CharSequence::toString).orElse("");

            NettyUtils.setAttr(ctx.channel(), NettyUtils.TOKEN, token);
            NettyUtils.setAttr(ctx.channel(), NettyUtils.UID, userUuid);

            ctx.pipeline().remove(this);
            ctx.fireChannelRead(request);
        }else {
            ctx.fireChannelRead(msg);
        }
    }
}

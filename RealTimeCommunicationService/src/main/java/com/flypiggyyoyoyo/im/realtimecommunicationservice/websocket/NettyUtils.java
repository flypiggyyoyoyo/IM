package com.flypiggyyoyoyo.im.realtimecommunicationservice.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

// NettyUtils 是 Netty 通道属性管理的核心工具
// 通过 AttributeKey 实现了跨处理器的数据共享
// 支持用户认证、会话管理和 WebSocket 握手等关键功能

@Slf4j
@ChannelHandler.Sharable
public class NettyUtils {
    // 定义属性键
    public static AttributeKey<String> TOKEN = AttributeKey.valueOf("token");
    public static AttributeKey<String> UID = AttributeKey.valueOf("userUuid");
    public static AttributeKey<WebSocketServerHandshaker> HANDSHAKER_ATTR_KEY = AttributeKey.valueOf(WebSocketServerHandshaker.class, "HANDSHAKER");

    // 设置属性
    public static <T> void setAttr(Channel channel, AttributeKey<T> attributeKey, T data) {
        Attribute<T> attr = channel.attr(attributeKey);
        attr.set(data);
    }

    // 获取属性
    public static <T> T getAttr(Channel channel, AttributeKey<T> key) {

        return channel.attr(key).get();
    }

}

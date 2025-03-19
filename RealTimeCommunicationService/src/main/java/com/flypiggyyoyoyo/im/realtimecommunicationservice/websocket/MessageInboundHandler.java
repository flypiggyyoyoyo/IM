package com.flypiggyyoyoyo.im.realtimecommunicationservice.websocket;


import cn.hutool.json.JSONUtil;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.constants.UserConstants;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.model.MessageDTO;
import io.jsonwebtoken.Claims;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.flypiggyyoyoyo.im.realtimecommunicationservice.constants.MessageTypeEnum;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.model.AckData;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.model.LogOutData;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.utils.JwtUtil;

import java.net.InetAddress;


//MessageInboundHandler 是 WebSocket 服务器的核心组件
//负责消息路由、连接管理、会话存储和安全校验，支撑高并发实时通信场景

@Slf4j
@ChannelHandler.Sharable
@AllArgsConstructor//Lombok 注解会为所有非静态、非瞬态字段生成全参构造函数


public class MessageInboundHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //当 Netty 接收到 TextWebSocketFrame 类型的消息时，就会调用 channelRead0 方法

    private StringRedisTemplate redisTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        try {
            log.info("服务端收到了消息：{}", msg.text());

//          msg.text() 获取 WebSocket 文本帧中的字符串内容，这通常是一个 JSON 格式的字符串
//          JSONUtil.toBean() 是 Hutool 工具库中的方法，用于将 JSON 字符串反序列化为 Java 对象
//          MessageDTO.class 指定了要转换成的目标类型
//          最终结果是将 JSON 字符串解析并转换为 MessageDTO 类型的对象
            MessageDTO messageDTO = JSONUtil.toBean(msg.text(), MessageDTO.class);

            MessageTypeEnum messageType = MessageTypeEnum.of(messageDTO.getType());

            // 消息类型路由
            switch (messageType) {
                case ACK:
                    processACK(messageDTO);
                    break;
                case LOG_OUT:
                    processLogOut(ctx, messageDTO);
                    break;
                case HEART_BEAT:
                    processHeartBeat(ctx, messageDTO);
                    break;
                default:  // 这里会处理 ILLEGAL 类型
                    processIllegal(ctx, messageDTO);
            }
        } catch (Exception e) {
            log.error("处理消息时发生异常", e);
            // 发送错误响应
            MessageDTO errorMsg = new MessageDTO();
            errorMsg.setType(MessageTypeEnum.ILLEGAL.getCode());
            errorMsg.setData("服务器处理消息异常");
            TextWebSocketFrame frame = new TextWebSocketFrame(JSONUtil.toJsonStr(errorMsg));
            ctx.channel().writeAndFlush(frame);
        }
    }

    private void processACK(MessageDTO msg) {
        // 处理客户端成功返回的数据
        AckData ackData = JSONUtil.toBean(msg.getData().toString(), AckData.class);
        log.info("ackData:{}", ackData);
        log.info("推送消息成功！");
    }

    private void processLogOut(ChannelHandlerContext ctx, MessageDTO msg) {
        LogOutData logOutData = JSONUtil.toBean(msg.getData().toString(), LogOutData.class);
        Integer userUuid = logOutData.getUserUuid();
        log.info("请求断开用户{}的连接...", userUuid);
        offline(ctx);
        log.info("断开连接成功！");
    }

    private void processHeartBeat(ChannelHandlerContext ctx, MessageDTO msg) {
        log.info("收到心跳包");
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setType(MessageTypeEnum.HEART_BEAT.getCode());// 设置心跳类型

        //将 Java 对象 messageDTO 序列化为 JSON 字符串
        TextWebSocketFrame frame = new TextWebSocketFrame(JSONUtil.toJsonStr(messageDTO));

        ctx.channel().writeAndFlush(frame);
    }

    //    private void processIllegal(ChannelHandlerContext ctx, MessageDTO msg){
//        throw new MessageTypeException("不支持的消息格式！");
//    }
    private void processIllegal(ChannelHandlerContext ctx, MessageDTO msg) {
        log.error("不支持的消息格式：{}", msg);

        // 向客户端发送错误响应
        MessageDTO errorMsg = new MessageDTO();
        errorMsg.setType(MessageTypeEnum.ILLEGAL.getCode());
        errorMsg.setData("不支持的消息格式");
        TextWebSocketFrame frame = new TextWebSocketFrame(JSONUtil.toJsonStr(errorMsg));
        ctx.channel().writeAndFlush(frame);

        // 根据业务需求决定是否断开连接
        // offline(ctx);
    }


    // 管道打开时自动调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("websocket has build");
    }

    // 当管道关闭时自动调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        offline(ctx);

        super.channelInactive(ctx);
    }


    // 在 Netty 框架中，事件驱动是核心机制
    // 当特定事件发生时，Netty 会遍历 ChannelPipeline 里的 ChannelHandler
    // 并调用每个 ChannelHandler 的 userEventTriggered 方法，把事件对象传递进去
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 处理心跳
        // 判断 evt 对象是否为 IdleStateEvent 类型的实例
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            switch (event.state()) {
                case READER_IDLE:
                    log.error("读空闲超时，关闭连接...{}, 用户ID{}", ctx.channel().remoteAddress(), ChannelManager.getUserByChannel(ctx.channel()));
                    offline(ctx);
                    break;
                case WRITER_IDLE:
                    log.error("写空闲超时");
                case ALL_IDLE:
                    log.error("读写空闲超时");
            }
        }
        // 处理握手，协议升级

        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            String token = NettyUtils.getAttr(ctx.channel(), NettyUtils.TOKEN);
            String userUuid = NettyUtils.getAttr(ctx.channel(), NettyUtils.UID);

            // 对 token 进行校验，不通过则直接进行关闭
            if (!validateToken(userUuid, token)) {
                log.info("token invalid");
                ctx.close();
                return;
            }

            // 将登录信息放入到 redis，用户与 netty 服务器的映射
            redisTemplate.opsForValue().set(UserConstants.USER_SESSION + userUuid, InetAddress.getLocalHost().getHostAddress());

            // 存储用户的管道信息
            Channel channel = ChannelManager.getChannelByUserId(userUuid);
            if (channel != null) {
                ChannelManager.removeUserChannel(userUuid);
                ChannelManager.removeChannelUser(channel);
                channel.close();
            }

            // 在将新的 channel 放入到其中
            ChannelManager.addUserChannel(userUuid, ctx.channel());
            ChannelManager.addChannelUser(userUuid, ctx.channel());
            log.info("客户连接成功， 用户ID：{}", userUuid + "管道地址： " + ctx.channel().remoteAddress());
        }

    }

    // 下线函数
    public void offline(ChannelHandlerContext ctx) {
        String userUuid = ChannelManager.getUserByChannel(ctx.channel());

        try {
            ChannelManager.removeChannelUser(ctx.channel());
            if (userUuid != null) {
                ChannelManager.removeUserChannel(userUuid);
                log.info("客户端关闭连接UserId：{}, 客户端地址为：{}", userUuid, ctx.channel().remoteAddress());
            }
        } catch (Exception e) {
            log.error("处理退出登录异常", e);
        } finally {
            // 关闭通道
            if (ctx.channel() != null) {
                ctx.channel().close();
            }
            // 在redis中删除对应的key
            redisTemplate.opsForValue().getAndDelete(UserConstants.USER_SESSION + userUuid);
        }
    }

    private boolean validateToken(String userUuid, String token) {

//        return true;
        Claims claims = JwtUtil.parse(token);
        String userId = claims.getSubject();

        // 校验不通过则直接返回 false
        return userId != null && userId.equals(userUuid);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("捕获到异常：", cause);

        try {
            offline(ctx);
        } catch (Exception e) {
            log.error("关闭管道失败", e);
        }
    }
}

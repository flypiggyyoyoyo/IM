package com.flypiggyyoyoyo.im.realtimecommunicationservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.constants.MessageRcvTypeEnum;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.constants.PushTypeEnum;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.data.ReceiveMessage.ReceiveMessageRequest;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.exception.ServiceException;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.model.*;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.websocket.ChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NettyMessageService {
    public void sendPush(PushTypeEnum pushType, Object data, String receiveUserUuid) {
        if (pushType == null || data == null || receiveUserUuid == null) {
            log.error("推送消息的类型、数据或接收用户UUID为空！");
            throw new ServiceException("用户" + receiveUserUuid + "的通道不可用或不活跃，推送消息失败。");
        }

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setType(pushType.getCode());
        messageDTO.setData(data);

        Channel channel = ChannelManager.getChannelByUserId(receiveUserUuid);
        if (channel != null && channel.isActive()) {
            log.info("准备发送消息，channel 状态: active={}, id={}, 发送内容: {}",
                    channel.isActive(),
                    channel.id(),
                    JSONUtil.toJsonStr(messageDTO));
            // 创建 WebSocket 帧
            TextWebSocketFrame frame = new TextWebSocketFrame(JSONUtil.toJsonStr(messageDTO));
            // 发送消息并添加监听器来处理发送结果
            channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("消息发送成功: {}", messageDTO);
                    } else {
                        log.error("消息发送失败: {}", future.cause());
                    }
                }
            });
        }
    }

    public void sendMessageToUser(ReceiveMessageRequest message) {
        switch (MessageRcvTypeEnum.fromCode(message.getType())) {
            case TEXT_MESSAGE:
                TextMessage textMessage = new TextMessage();

                //把message的信息复制到textMessage中
                BeanUtils.copyProperties(message, textMessage);

                // 依据 TextMessageBody 类创建一个新对象
                // 并且把 message.getBody() 对象里的属性值复制到新对象对应的属性中
                TextMessageBody textBean = BeanUtil.toBean(message.getBody(), TextMessageBody.class);

                textMessage.setBody(textBean);
                log.info("textMessage:{}", textMessage);
                List<Long> textReceiveUserIds = textMessage.getReceiveUserIds();

                //释放内存
                textMessage.setReceiveUserIds(null);

                for (Long textReceiveUser : textReceiveUserIds) {
                    log.info("textReceiveUser:{}", textReceiveUser);
                    log.info("是否存在管道: {}", ChannelManager.getChannelByUserId(textReceiveUser.toString()));
                    if (ChannelManager.getChannelByUserId(textReceiveUser.toString()) != null) {
                        log.info("调用 sendPush: {}", textReceiveUser);
                        sendPush(PushTypeEnum.MESSAGE_NOTIFICATION, textMessage, textReceiveUser.toString());
                    }
                }
                break;
            case PICTURE_MESSAGE:
                PictureMessage pictureMessage = new PictureMessage();
                BeanUtils.copyProperties(message, pictureMessage);
                PictureMessageBody pictureBean = BeanUtil.toBean(message.getBody(), PictureMessageBody.class);
                pictureMessage.setBody(pictureBean);
                log.info("pictureMessage:{}", pictureMessage);
                List<Long> pictureReceiveUserIds = pictureMessage.getReceiveUserIds();
                for (Long pictureReceiveUser : pictureReceiveUserIds) {
                    if (ChannelManager.getChannelByUserId(pictureReceiveUser.toString()) != null) {
                        sendPush(PushTypeEnum.MESSAGE_NOTIFICATION, pictureMessage, pictureReceiveUser.toString());
                    }
                }
                break;
        }
    }
}

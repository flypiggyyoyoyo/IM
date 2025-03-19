package com.flypiggyyoyoyo.im.realtimecommunicationservice.data.ReceiveMessage;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ReceiveMessageRequest {
    private List<Long> receiveUserIds;

    private String sendUserId;

    private String sessionId;

    private String avatar;

    private String userName;

    //消息类型
    private Integer type;

    private String messageId;

    private Integer sessionType;

    private String sessionName;

    private String sessionAvatar;

    private String createdAt;

    private Object body;
}
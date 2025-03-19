package com.flypiggyyoyoyo.im.realtimecommunicationservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

//封装客户端发送的确认消息
public class AckData {

    private Long sessionId;

    private Long receiveUserUuid;

    private String msgUuid;
}
package com.flypiggyyoyoyo.im.messageservice.data.sendMsg;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SendMsgRequest {
    private Long sessionId;

    private Long sendUserId;

    private Integer sessionType;

    private Integer type;

    private Long receiveUserId;

    private Object body;
}

package com.flypiggyyoyoyo.im.messageservice.data.sendRedPackage;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class SendRedPacketRequest {
    private Long sessionId;

    private Long receiveUserId;

    private Long sendUserId;

    private Integer type;

    private Integer sessionType;

    private Body body;

    @Data
    @Accessors(chain = true)
    public static class Body {

        private Integer redPacketType;

        private BigDecimal totalAmount;

        private Integer totalCount;

        private String redPacketWrapperText;
    }
}
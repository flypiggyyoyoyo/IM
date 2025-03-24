package com.flypiggyyoyoyo.im.messageservice.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 红包主表
 * @TableName red_packet
 */
@TableName(value ="red_packet")
@Data
public class RedPacket {
    /**
     * 红包ID唯一标识
     */
    @TableId
    private Long redPacketId;

    /**
     * 发送者用户ID
     */
    private Long senderId;

    /**
     * 会话ID（单聊或群聊）
     */
    private Long sessionId;

    /**
     * 红包封面文案
     */
    private String redPacketWrapperText;

    /**
     * 红包类型：1普通红包，2拼手气红包
     */
    private Integer redPacketType;

    /**
     * 红包总金额
     */
    private BigDecimal totalAmount;

    /**
     * 红包总个数
     */
    private Integer totalCount;

    /**
     * 剩余金额
     */
    private BigDecimal remainingAmount;

    /**
     * 剩余个数
     */
    private Integer remainingCount;

    /**
     * 状态：1未领取完，2已领取完，3已过期
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RedPacket other = (RedPacket) that;
        return (this.getRedPacketId() == null ? other.getRedPacketId() == null : this.getRedPacketId().equals(other.getRedPacketId()))
            && (this.getSenderId() == null ? other.getSenderId() == null : this.getSenderId().equals(other.getSenderId()))
            && (this.getSessionId() == null ? other.getSessionId() == null : this.getSessionId().equals(other.getSessionId()))
            && (this.getRedPacketWrapperText() == null ? other.getRedPacketWrapperText() == null : this.getRedPacketWrapperText().equals(other.getRedPacketWrapperText()))
            && (this.getRedPacketType() == null ? other.getRedPacketType() == null : this.getRedPacketType().equals(other.getRedPacketType()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getTotalCount() == null ? other.getTotalCount() == null : this.getTotalCount().equals(other.getTotalCount()))
            && (this.getRemainingAmount() == null ? other.getRemainingAmount() == null : this.getRemainingAmount().equals(other.getRemainingAmount()))
            && (this.getRemainingCount() == null ? other.getRemainingCount() == null : this.getRemainingCount().equals(other.getRemainingCount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRedPacketId() == null) ? 0 : getRedPacketId().hashCode());
        result = prime * result + ((getSenderId() == null) ? 0 : getSenderId().hashCode());
        result = prime * result + ((getSessionId() == null) ? 0 : getSessionId().hashCode());
        result = prime * result + ((getRedPacketWrapperText() == null) ? 0 : getRedPacketWrapperText().hashCode());
        result = prime * result + ((getRedPacketType() == null) ? 0 : getRedPacketType().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getTotalCount() == null) ? 0 : getTotalCount().hashCode());
        result = prime * result + ((getRemainingAmount() == null) ? 0 : getRemainingAmount().hashCode());
        result = prime * result + ((getRemainingCount() == null) ? 0 : getRemainingCount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", redPacketId=").append(redPacketId);
        sb.append(", senderId=").append(senderId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", redPacketWrapperText=").append(redPacketWrapperText);
        sb.append(", redPacketType=").append(redPacketType);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", remainingAmount=").append(remainingAmount);
        sb.append(", remainingCount=").append(remainingCount);
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}
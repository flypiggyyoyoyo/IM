package com.flypiggyyoyoyo.im.offlinedatastoreservice.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message {
    /**
     * 
     */
    @TableId
    private Long messageId;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 会话id
     */
    private Long sessionId;

    /**
     * 消息类型。1文本消息，2图片消息，3文件消息，4视频消息，5红包，6表情包
     */
    private Integer type;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 
     */
    private Long replyId;

    /**
     * 会话类型。1单聊，2群聊
     */
    private Integer sessionType;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

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
        Message other = (Message) that;
        return (this.getMessageId() == null ? other.getMessageId() == null : this.getMessageId().equals(other.getMessageId()))
            && (this.getSenderId() == null ? other.getSenderId() == null : this.getSenderId().equals(other.getSenderId()))
            && (this.getSessionId() == null ? other.getSessionId() == null : this.getSessionId().equals(other.getSessionId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReplyId() == null ? other.getReplyId() == null : this.getReplyId().equals(other.getReplyId()))
            && (this.getSessionType() == null ? other.getSessionType() == null : this.getSessionType().equals(other.getSessionType()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessageId() == null) ? 0 : getMessageId().hashCode());
        result = prime * result + ((getSenderId() == null) ? 0 : getSenderId().hashCode());
        result = prime * result + ((getSessionId() == null) ? 0 : getSessionId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReplyId() == null) ? 0 : getReplyId().hashCode());
        result = prime * result + ((getSessionType() == null) ? 0 : getSessionType().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", senderId=").append(senderId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", replyId=").append(replyId);
        sb.append(", sessionType=").append(sessionType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

    @TableField(exist = false)
    private RedPacket redPacket;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
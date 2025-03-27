package com.flypiggyyoyoyo.im.offlinedatastoreservice.common;

import lombok.Data;

@Data
public class TextMessageBody {
    private String content;
    private Long replyId;
}

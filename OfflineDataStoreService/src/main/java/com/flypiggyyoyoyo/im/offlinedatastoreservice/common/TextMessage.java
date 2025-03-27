package com.flypiggyyoyoyo.im.offlinedatastoreservice.common;

import lombok.Data;

@Data
public class TextMessage extends MessageBody {
    private TextMessageBody body;

    @Override
    public String toString(){
        return super.toString();
    }
}
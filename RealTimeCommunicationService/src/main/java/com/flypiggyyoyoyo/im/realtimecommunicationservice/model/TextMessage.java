package com.flypiggyyoyoyo.im.realtimecommunicationservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
//消息类型的整体封装，元数据+方法等
public class TextMessage extends Message {

    //消息元数据的封装
    private TextMessageBody body;

    @Override
    public String toString() {
        return super.toString();
    }
}

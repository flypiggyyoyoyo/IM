package com.flypiggyyoyoyo.im.realtimecommunicationservice.constants;

//消息类型枚举
public enum MessageRcvTypeEnum {
    TEXT_MESSAGE(1),
    PICTURE_MESSAGE(2),
    FILE_MESSAGE(3),
    VIDEO_MESSAGE(4),
    RED_PACKET_MESSAGE(5),
    EMOTICON_MESSAGE(6);


    private Integer code;
    MessageRcvTypeEnum(Integer code){
        this.code = code;
    }

    // MessageRcvTypeEnum.values() 是 Java 枚举类型自动提供的一个方法。
    // 此方法会返回一个包含该枚举类型所有枚举常量的数组
    public static MessageRcvTypeEnum fromCode(int code) {
        for (MessageRcvTypeEnum type : MessageRcvTypeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public Integer getCode(){
        return this.code;
    }
}
package com.flypiggyyoyoyo.im.messageservice.constants;

public enum SessionType {
    SINGLE(1),
    GROUP(2);
    private final int value;

    SessionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

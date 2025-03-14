package com.flypiggyyoyoyo.im.authenticationservice.constants.config;

import lombok.Getter;

@Getter

public enum TimeOutEnum {
    JWT_TIME_OUT("token time out(day)","jwt:",24);

    private final String name;

    private final String profix;

    private final int timeOut;

    TimeOutEnum(String name, String profix, int timeOut) {
        this.name = name;
        this.profix = profix;
        this.timeOut = timeOut;
    }
}

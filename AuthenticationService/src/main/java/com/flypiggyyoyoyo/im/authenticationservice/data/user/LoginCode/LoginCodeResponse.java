package com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginCodeResponse {
    private String userId;
    private String userName;
    private String avator;
    private String signature;
    private Integer gender;
    private Integer status;
    private String token;
}


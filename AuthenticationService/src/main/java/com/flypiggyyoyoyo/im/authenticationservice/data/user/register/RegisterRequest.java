package com.flypiggyyoyoyo.im.authenticationservice.data.user.register;

import lombok.Data;

@Data

public class RegisterRequest {
    private String phone;
    private String password;
    private String code;
}

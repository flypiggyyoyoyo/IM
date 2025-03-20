package com.flypiggyyoyoyo.im.authenticationservice.data.user.login;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Lang;

@Data
@Accessors(chain = true)
public class LoginResponse{
    private Long userId;
    private String userName;
    private String avatar;
    private String signature;
    private Integer gender;
    private Integer status;
    private String token;
}

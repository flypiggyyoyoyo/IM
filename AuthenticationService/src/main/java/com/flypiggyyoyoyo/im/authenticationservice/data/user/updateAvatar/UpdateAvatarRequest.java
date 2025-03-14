package com.flypiggyyoyoyo.im.authenticationservice.data.user.updateAvatar;

import javax.validation.constraints.NotEmpty;

public class UpdateAvatarRequest {
    //AvatarUrl 头像地址
    @NotEmpty(message="头像地址不为空")
    public String avatarUrl;
}

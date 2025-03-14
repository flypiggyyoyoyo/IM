package com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class LoginCodeRequest {
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11,max = 11,message = "手机号应为11位")
    private String phone;
    @NotEmpty(message = "验证码不能为空")
    @Length(min = 6,max = 6,message = "验证码应为6位")
    private String code;
}

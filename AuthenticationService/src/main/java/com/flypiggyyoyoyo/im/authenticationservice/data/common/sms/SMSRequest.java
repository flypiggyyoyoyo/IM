package com.flypiggyyoyoyo.im.authenticationservice.data.common.sms;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SMSRequest {
    private String phone;
}

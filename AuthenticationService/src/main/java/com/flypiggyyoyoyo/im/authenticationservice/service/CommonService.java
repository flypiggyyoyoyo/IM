package com.flypiggyyoyoyo.im.authenticationservice.service;

import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSResponse;

public interface CommonService {
    SMSResponse sendSMS(SMSRequest request) throws Exception;
}

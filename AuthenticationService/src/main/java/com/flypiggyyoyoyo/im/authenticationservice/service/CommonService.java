package com.flypiggyyoyoyo.im.authenticationservice.service;

import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.uploadUrl.UploadUrlRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.uploadUrl.UploadUrlResponse;

public interface CommonService {
    SMSResponse sendSms(SMSRequest request) throws Exception;
    UploadUrlResponse uploadUrl(UploadUrlRequest request) throws Exception;
}

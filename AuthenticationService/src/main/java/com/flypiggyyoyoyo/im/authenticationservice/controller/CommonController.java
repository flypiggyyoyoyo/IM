package com.flypiggyyoyoyo.im.authenticationservice.controller;

import com.flypiggyyoyoyo.im.authenticationservice.common.Result;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.uploadUrl.UploadUrlRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.uploadUrl.UploadUrlResponse;
import com.flypiggyyoyoyo.im.authenticationservice.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/user/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/sms")
    public Result<SMSResponse> sendSms(@Valid SMSRequest request) throws Exception {
        SMSResponse response = commonService.sendSms(request);

        return Result.OK(response);
    }

    @GetMapping("/uploadUrl")
    public Result<UploadUrlResponse> getUploadUrl(@Valid UploadUrlRequest request) throws Exception {
        UploadUrlResponse response = commonService.uploadUrl(request);

        return Result.OK(response);
    }
}

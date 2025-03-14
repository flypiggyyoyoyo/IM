package com.flypiggyyoyoyo.im.authenticationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.authenticationservice.constants.config.OSSConstant;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.registerConstant;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.sms.SMSResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.uploadUrl.UploadUrlRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.common.uploadUrl.UploadUrlResponse;
import com.flypiggyyoyoyo.im.authenticationservice.mapper.UserMapper;
import com.flypiggyyoyoyo.im.authenticationservice.model.User;
import com.flypiggyyoyoyo.im.authenticationservice.service.CommonService;
import com.flypiggyyoyoyo.im.authenticationservice.service.UserService;
import com.flypiggyyoyoyo.im.authenticationservice.utils.OSSUtils;
import com.flypiggyyoyoyo.im.authenticationservice.utils.RandomNumUtil;
import com.flypiggyyoyoyo.im.authenticationservice.utils.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CommonServiceImpl extends ServiceImpl<UserMapper, User>
        implements CommonService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OSSUtils ossUtils;

    @Override
    public SMSResponse sendSms(SMSRequest request) throws Exception {
        String phone = request.getPhone();
        String code = RandomNumUtil.getRandomNum();

        //存入redis
        redisTemplate.opsForValue().set(registerConstant.REGISTER_CODE + phone, code,5, TimeUnit.SECONDS);

        new SMSUtil().sendServiceSms(phone,code);
        return new SMSResponse().setPhone(phone);
    }

    @Override
    public UploadUrlResponse uploadUrl(UploadUrlRequest request) throws Exception {
        String fileName = request.getFileName();

        String uploadUrl = ossUtils.uploadFile(OSSConstant.BUCKET_NAME,fileName,OSSConstant.PICTURE_EXPIRE_TIME);
        String downUrl = ossUtils.downUrl(OSSConstant.BUCKET_NAME,fileName);

        UploadUrlResponse response = new UploadUrlResponse();
        response.setUploadUrl(uploadUrl)
                .setDownloadUrl(downUrl);

        return response;
    }
}

package com.flypiggyyoyoyo.im.authenticationservice.utils;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;

import com.flypiggyyoyoyo.im.authenticationservice.constants.config.ConfigEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SMSUtil {

    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(ConfigEnum.SMS_ACCESS_KEY_ID.getValue())
                .setAccessKeySecret(ConfigEnum.SMS_ACCESS_KEY_SECRET.getValue());
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public void sendServiceSms(String phoneNumber, String code) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = SMSUtil.createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName(ConfigEnum.SMS_SIG_NAME.getValue())
                .setTemplateCode(ConfigEnum.SMS_TEMPLATE_CODE.getValue())
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            System.out.println(phoneNumber+' '+code);
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtime);

            // 检查响应状态码
            if (!"OK".equalsIgnoreCase(response.getBody().getCode())) {
                String errorMsg = "短信发送失败，response code: " + response.getBody().getCode() +
                        ", message: " + response.getBody().getMessage();
                log.error(errorMsg);
                throw new RuntimeException(errorMsg);
            }

            log.info("短信发送成功，response: {}", response);
        } catch (TeaException error) {
            String errorMsg = "短信发送失败，error: " + error.message;
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            String errorMsg = "短信发送失败，error: " + error.message;
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
    }


}
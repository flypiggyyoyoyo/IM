package com.flypiggyyoyoyo.im.offlinedatastoreservice.service;

import com.flypiggyyoyoyo.im.offlinedatastoreservice.data.offlineMessage.OfflineMessageRequest;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.data.offlineMessage.OfflineMessageResponse;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author flypiggy
* @description 针对表【message】的数据库操作Service
* @createDate 2025-03-27 21:26:43
*/
public interface MessageService extends IService<Message> {
    OfflineMessageResponse getOfflineMessage(OfflineMessageRequest request);

    void saveOfflineMessage(String message);
}

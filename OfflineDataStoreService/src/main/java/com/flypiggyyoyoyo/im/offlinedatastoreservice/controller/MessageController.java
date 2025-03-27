package com.flypiggyyoyoyo.im.offlinedatastoreservice.controller;

import com.flypiggyyoyoyo.im.offlinedatastoreservice.common.Result;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.data.offlineMessage.OfflineMessageRequest;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.data.offlineMessage.OfflineMessageResponse;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/offline")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public Result<OfflineMessageResponse> getOfflineMessage(@Valid OfflineMessageRequest request) {
        OfflineMessageResponse offlineMessageResponse = messageService.getOfflineMessage(request);

        return Result.ok(offlineMessageResponse);
    }
}

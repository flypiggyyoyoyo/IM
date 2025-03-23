package com.flypiggyyoyoyo.im.messageservice.controller;

import com.flypiggyyoyoyo.im.messageservice.common.Result;
import com.flypiggyyoyoyo.im.messageservice.data.sendMsg.SendMsgRequest;
import com.flypiggyyoyoyo.im.messageservice.data.sendMsg.SendMsgResponse;
import com.flypiggyyoyoyo.im.messageservice.feign.ContactServiceFeign;
import com.flypiggyyoyoyo.im.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SendMsgController {
    @Autowired
    private ContactServiceFeign contactServiceFeign;

    @Autowired
    private MessageService messageService;


    @GetMapping("/feign")
    public Result<?> getUser() {

        Result<?> user = contactServiceFeign.getUser();

        return Result.OK(user);
    }

    @PostMapping("/v1/chat/session")
    public Result<SendMsgResponse> sendMsg(@RequestBody SendMsgRequest request) throws Exception {
        SendMsgResponse response = messageService.sendMessage(request);

        return Result.OK(response);
    }


}

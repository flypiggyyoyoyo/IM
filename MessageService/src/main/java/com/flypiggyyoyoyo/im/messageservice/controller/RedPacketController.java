package com.flypiggyyoyoyo.im.messageservice.controller;

import com.flypiggyyoyoyo.im.messageservice.common.Result;
import com.flypiggyyoyoyo.im.messageservice.data.getRedPacket.RedPacketResponse;
import com.flypiggyyoyoyo.im.messageservice.data.receiveRedPackage.ReceiveRedPacketRequest;
import com.flypiggyyoyoyo.im.messageservice.data.receiveRedPackage.ReceiveRedPacketResponse;
import com.flypiggyyoyoyo.im.messageservice.data.sendRedPackage.SendRedPacketRequest;
import com.flypiggyyoyoyo.im.messageservice.data.sendRedPackage.SendRedPacketResponse;
import com.flypiggyyoyoyo.im.messageservice.mapper.RedPacketMapper;
import com.flypiggyyoyoyo.im.messageservice.service.GetRedPacketService;
import com.flypiggyyoyoyo.im.messageservice.service.RedPacketReceiveService;
import com.flypiggyyoyoyo.im.messageservice.service.RedPacketService;
import com.flypiggyyoyoyo.im.messageservice.util.PreventDuplicateSubmit;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat/redPacket")
public class RedPacketController {

    @Autowired
    private RedPacketService redPacketService;

    @Autowired
    private RedPacketReceiveService redPacketReceiveService;

    @Autowired
    private GetRedPacketService getRedPacketService;

    @SneakyThrows
    @PreventDuplicateSubmit // 防止重复提交
    @PostMapping("/send")
    public Result<SendRedPacketResponse> sendRedPacket(@RequestBody SendRedPacketRequest request) {
        SendRedPacketResponse response = redPacketService.sendRedPacket(request);

        return Result.OK(response);
    }

    @SneakyThrows
    @PostMapping("/receive")
    public Result<ReceiveRedPacketResponse> receiveRedPacket(@RequestBody ReceiveRedPacketRequest request) {
        ReceiveRedPacketResponse response = redPacketReceiveService.receiveRedPacket(request.getUserId(), request.getRedPacketId());

        return Result.OK(response);
    }

    @GetMapping("/{redPacketId}")
    public Result<RedPacketResponse> getRedPacket(@PathVariable Long redPacketId,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {

        RedPacketResponse response = getRedPacketService.getRedPacketDetails(redPacketId, pageNum, pageSize);

        return Result.OK(response);
    }
}

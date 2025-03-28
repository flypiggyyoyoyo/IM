package com.flypiggyyoyoyo.im.momentservice.controller;

import com.flypiggyyoyoyo.im.momentservice.common.Result;
import com.flypiggyyoyoyo.im.momentservice.data.createMoment.CreateMomentRequest;
import com.flypiggyyoyoyo.im.momentservice.data.createMoment.CreateMomentResponse;
import com.flypiggyyoyoyo.im.momentservice.service.MomentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/moment")
@RequiredArgsConstructor
public class MomentController {
    @Autowired
    private MomentService momentService;

    @PostMapping("")
    public Result<CreateMomentResponse> createMoment(@Valid @RequestBody CreateMomentRequest request) {
        CreateMomentResponse responseResult = momentService.createMoment(request);
        return Result.OK(responseResult);
    }
}

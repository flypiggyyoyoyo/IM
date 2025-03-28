package com.flypiggyyoyoyo.im.momentservice.service.Impl;

import com.flypiggyyoyoyo.im.momentservice.data.createMoment.CreateMomentRequest;
import com.flypiggyyoyoyo.im.momentservice.data.createMoment.CreateMomentResponse;
import com.flypiggyyoyoyo.im.momentservice.service.MomentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MomentServiceImpl implements MomentService {

    @Override
    public CreateMomentResponse createMoment(CreateMomentRequest request) {
        return null;
    }
}

package com.flypiggyyoyoyo.im.momentservice.service;

import com.flypiggyyoyoyo.im.momentservice.common.Result;
import com.flypiggyyoyoyo.im.momentservice.data.createMoment.CreateMomentRequest;
import com.flypiggyyoyoyo.im.momentservice.data.createMoment.CreateMomentResponse;

public interface MomentService {
    CreateMomentResponse createMoment(CreateMomentRequest request);
}

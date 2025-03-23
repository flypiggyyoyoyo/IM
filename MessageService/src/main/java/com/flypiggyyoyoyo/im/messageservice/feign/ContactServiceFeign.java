package com.flypiggyyoyoyo.im.messageservice.feign;

import com.flypiggyyoyoyo.im.messageservice.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient("ContactService")
public interface ContactServiceFeign {
    @GetMapping("/api/v1/contact/user")
    Result<?> getUser();
}

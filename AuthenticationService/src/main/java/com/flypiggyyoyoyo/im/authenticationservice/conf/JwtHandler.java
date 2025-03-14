package com.flypiggyyoyoyo.im.authenticationservice.conf;

import com.alibaba.fastjson.JSON;
import com.aliyun.credentials.utils.StringUtils;
import com.flypiggyyoyoyo.im.authenticationservice.common.Result;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)){
            refuseResult(response);

            return false;
        }
        // 验证 jwt 逻辑


        return true;
    }

    public void refuseResult(HttpServletResponse httpServletResponse) throws Exception{
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        Result<Object> result = new Result<>().setCode(ErrorEnum.SIGNATURE_VERIFICATION_FAILED.getCode()).setMsg(ErrorEnum.SIGNATURE_VERIFICATION_FAILED.getMessage());
        httpServletResponse.getWriter().print(JSON.toJSONString(result));
        httpServletResponse.getWriter().flush();
    }
}

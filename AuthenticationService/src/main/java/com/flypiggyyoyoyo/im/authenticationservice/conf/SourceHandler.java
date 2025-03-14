package com.flypiggyyoyoyo.im.authenticationservice.conf;

import com.alibaba.fastjson.JSON;
import com.flypiggyyoyoyo.im.authenticationservice.common.Result;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求来源验证拦截器
 *
 * 这个拦截器用于验证请求是否来自特定的网关，是一种安全措施，
 * 防止API被绕过网关直接访问。所有请求必须包含特定的X-Request-Source请求头
 * 并且其值必须是"InfiniteChat-GateWay"，否则请求会被拒绝。
 */
@Slf4j
@Component
class SourceHandler implements HandlerInterceptor {
    /**
     * 请求预处理方法，在Controller方法执行前调用
     * 实现了HandlerInterceptor接口的preHandle方法
     *
     * @param request 当前HTTP请求
     * @param response 当前HTTP响应
     * @param handler 当前请求对应的处理器（通常是Controller中的方法）
     * @return true表示继续处理请求，false表示拦截请求不再继续
     * @throws Exception 如果处理过程中出现异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头中获取X-Request-Source的值
        // 按照注释，这个值应该是"InfiniteChat-GateWay"
        // 变量名应该使用小写开头，遵循Java命名规范
        String Handler = request.getHeader("X-Request-Source");

        // 检查请求头值是否等于预期值
        // 使用equals方法比较字符串，将常量放在前面可以避免空指针异常
        if(!"InfiniteChat-GateWay".equals(Handler)) {
            // 如果请求来源不合法，调用refuseResult方法拒绝请求
            refuseResult(response);
            // 返回false，终止后续处理流程
            return false;
        }

        // 请求来源验证通过，返回true继续处理请求
        return true;
    }

    /**
     * 处理非法请求来源的响应
     * 设置适当的HTTP状态码和响应内容
     *
     * @param httpServletResponse 用于发送响应的HttpServletResponse对象
     * @throws Exception 如果处理过程中出现异常
     */
    public void refuseResult(HttpServletResponse httpServletResponse) throws Exception {
        // 设置响应的内容类型
        httpServletResponse.setContentType("text/html;charset=UTF-8");

        // 设置字符编码
        httpServletResponse.setCharacterEncoding("UTF-8");

        // 设置HTTP状态码为403 Forbidden
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

        Result<Object> result = new Result<>()
                .setCode(ErrorEnum.ILLEGAL_SOURCE.getCode())
                .setMsg(ErrorEnum.ILLEGAL_SOURCE.getMessage());

        // 将Result对象转换为JSON字符串并写入响应
        httpServletResponse.getWriter().print(JSON.toJSONString(result));

        // 刷新响应输出流，确保数据被发送
        httpServletResponse.getWriter().flush();
    }
}

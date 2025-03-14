package com.flypiggyyoyoyo.im.authenticationservice.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.aliyun.credentials.utils.StringUtils;
import com.flypiggyyoyoyo.im.authenticationservice.constants.config.ConfigEnum;
import com.flypiggyyoyoyo.im.authenticationservice.constants.config.TimeOutEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

public class JwtUtil {
    // 定义一个静态常量 expiration，用于存储 JWT 令牌的过期时间
    // 使用 Duration 类来表示一段时间，Duration.ofHours 方法用于创建一个以小时为单位的时间段
    // TimeOutEnum.JWT_TIME_OUT.getTimeOut() 是从枚举类 TimeOutEnum 中获取 JWT 令牌的超时时间（以小时为单位）
    // 最终将该超时时间转换为 Duration 对象，存储在 expiration 常量中
    private final static Duration expiration = Duration.ofHours(TimeOutEnum.JWT_TIME_OUT.getTimeOut());

    /**
     * 生成 JWT（JSON Web Token）令牌的静态方法
     *
     * @param UserId 用户的唯一标识符，通常是用户的 ID，用于作为 JWT 令牌的主题（subject）
     * @return 生成的 JWT 令牌字符串
     */
    public static String generate(String UserId) {
        // 计算 JWT 令牌的过期日期
        // System.currentTimeMillis() 获取当前系统时间的毫秒数
        // expiration.toMillis() 将之前定义的过期时间（Duration 对象）转换为毫秒数
        // 两者相加得到过期时间的毫秒数，然后使用 Date 类将其转换为日期对象
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());

        // 使用 Jwts.builder() 方法创建一个 JWT 构建器对象
        // 通过链式调用的方式设置 JWT 的各种声明和签名信息
        return Jwts.builder()
                // 设置 JWT 的主题（subject），这里将用户的 ID 作为主题
                // 主题是 JWT 中一个重要的声明，用于标识该令牌所关联的用户或实体
                .setSubject(UserId)
                // 设置 JWT 的签发时间，即当前时间
                // new Date() 创建一个表示当前时间的日期对象
                .setIssuedAt(new Date())
                // 设置 JWT 的过期时间，即之前计算得到的 expiryDate
                // 客户端在使用该令牌时，服务器会检查当前时间是否超过了过期时间，如果超过则认为令牌无效
                .setExpiration(expiryDate)
                // 设置 JWT 的签名算法和签名密钥
                // SignatureAlgorithm.HS512 表示使用 HMAC-SHA512 算法进行签名
                // ConfigEnum.TOKEN_SECRET_KEY.getText() 从枚举类 ConfigEnum 中获取签名密钥
                // 签名密钥是一个保密的字符串，用于对 JWT 进行签名，确保令牌的完整性和真实性
                .signWith(SignatureAlgorithm.HS512, ConfigEnum.TOKEN_SECRET_KEY.getText())
                // 调用 compact() 方法将 JWT 构建器中的信息压缩成一个紧凑的字符串，即最终的 JWT 令牌
                .compact();
    }

    // 解析jwt
    public static io.jsonwebtoken.Claims parse(String token) throws JwtException {
        if (StringUtils.isEmpty(token)){
            throw new JwtException("token 为空");
        }

        // 这个Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        claims = Jwts.parser()
                .setSigningKey(ConfigEnum.TOKEN_SECRET_KEY.getValue()) // 设置秘钥
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}

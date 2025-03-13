package com.flypiggyyoyoyo.im.authenticationservice.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.ErrorEnum;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.registerConstant;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterResponse;
import com.flypiggyyoyoyo.im.authenticationservice.exception.CodeException;
import com.flypiggyyoyoyo.im.authenticationservice.exception.DatabaseException;
import com.flypiggyyoyoyo.im.authenticationservice.exception.UserException;
import com.flypiggyyoyoyo.im.authenticationservice.model.User;
import com.flypiggyyoyoyo.im.authenticationservice.service.UserService;
import com.flypiggyyoyoyo.im.authenticationservice.mapper.UserMapper;
import com.flypiggyyoyoyo.im.authenticationservice.utils.NickNameGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author flypiggy
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2025-03-12 23:59:59
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String phone = request.getPhone();
        String password = request.getPassword();

        //手机号是否注册
        if (isRegister(phone)) {
            throw new UserException(ErrorEnum.REGISTER_ERROR);
        }

        //从redis里取验证码
        String redisCode = redisTemplate.opsForValue().get(registerConstant.REGISTER_CODE + phone);

        //验证码是否正确
        if(redisCode == null || !redisCode.equals(request.getCode())) {
            throw new CodeException(ErrorEnum.CODE_ERROR);
        }

        //注册流程

        //雪花算法生成id
        Snowflake snowflake = new Snowflake(1, 1);

        //密文存储密码
        String encryptedPassword= DigestUtils.md5DigestAsHex(password.getBytes());

        //创建用户实体类
        User user = new User()
                .setPhone(phone)
                .setPassword(encryptedPassword)
                .setUserId(snowflake.nextId())
                .setUserName(NickNameGeneratorUtil.generateNickName());

        //存入数据库
        boolean isUserSave = this.save(user);

        if(!isUserSave) {
            throw new DatabaseException("数据库异常，存储信息失败");
        }

        return new RegisterResponse().setPhone(phone);
    }

    private boolean isRegister(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        long count = this.count(queryWrapper);
        return count > 0;
    }
}





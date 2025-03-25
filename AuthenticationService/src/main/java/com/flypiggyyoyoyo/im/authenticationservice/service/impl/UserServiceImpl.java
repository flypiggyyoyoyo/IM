package com.flypiggyyoyoyo.im.authenticationservice.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.ErrorEnum;
import com.flypiggyyoyoyo.im.authenticationservice.constants.user.registerConstant;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode.LoginCodeRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode.LoginCodeResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.login.LoginRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.login.LoginResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.updateAvatar.UpdateAvatarRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.updateAvatar.UpdateAvatarResponse;
import com.flypiggyyoyoyo.im.authenticationservice.exception.CodeException;
import com.flypiggyyoyoyo.im.authenticationservice.exception.DatabaseException;
import com.flypiggyyoyoyo.im.authenticationservice.exception.UserException;
import com.flypiggyyoyoyo.im.authenticationservice.mapper.UserBalanceMapper;
import com.flypiggyyoyoyo.im.authenticationservice.model.User;
import com.flypiggyyoyoyo.im.authenticationservice.model.UserBalance;
import com.flypiggyyoyoyo.im.authenticationservice.service.UserService;
import com.flypiggyyoyoyo.im.authenticationservice.mapper.UserMapper;
import com.flypiggyyoyoyo.im.authenticationservice.utils.JwtUtil;
import com.flypiggyyoyoyo.im.authenticationservice.utils.NickNameGeneratorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Autowired
    private UserBalanceMapper userBalanceMapper;

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

        UserBalance userBalance = new UserBalance()
                .setUserId(user.getUserId())
                .setBalance(BigDecimal.valueOf(1000))
                .setUpdatedAt(LocalDateTime.now());

        int insert = userBalanceMapper.insert(userBalance);
        if (insert <= 0){
            throw new DatabaseException("数据库异常，创建用户账户信息错误");
        }

        return new RegisterResponse().setPhone(phone);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        // 创建一个针对 User 实体类的 QueryWrapper 对象，QueryWrapper 是 MyBatis-Plus 提供的用于构建 SQL 查询条件的工具类
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 向 QueryWrapper 中添加一个等值查询条件，即查询 User 表中 phone 字段等于 request 对象中 getPhone() 方法返回值的记录
        //("字段名","要匹配的值")
        queryWrapper.eq("phone", request.getPhone());

        //取第一个一样的
        User user = this.getOnly(queryWrapper,true);

        //构造返回体
        LoginResponse response = new LoginResponse();
        BeanUtils.copyProperties(user, response);

        //签发token
        String token = JwtUtil.generate(String.valueOf(user.getUserId()));
        response.setToken(token);

        return response;
    }

    @Override
    public LoginCodeResponse loginCode(LoginCodeRequest request) {

        //验证码
        String redisCode = redisTemplate.opsForValue().get(registerConstant.REGISTER_CODE+request.getPhone());
        if (redisCode == null|| !redisCode.equals(request.getCode())){
            throw new CodeException(ErrorEnum.CODE_ERROR);
        }

        //通过手机号查询信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",request.getPhone());
        User user = this.getOne(queryWrapper,true);

        LoginCodeResponse response = new LoginCodeResponse();
        BeanUtils.copyProperties(user, response);

        //签发token
        String token = JwtUtil.generate(String.valueOf(user.getUserId()));
        response.setToken(token);

        return response;
    }

    @Override
    public UpdateAvatarResponse updateAvatar(String id, UpdateAvatarRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();//创建了一个空的 QueryWrapper 对象，后续可以向这个对象中添加各种查询条件
        queryWrapper.eq("user_id",Long.valueOf(id));//添加"="查询
        User user = this.getOnly(queryWrapper,true);
        if(user == null) {
            throw new UserException(ErrorEnum.NO_USER_ERROR);
        }

        user.setAvatar(request.avatarUrl);//预设

        boolean isUpdate = this.updateById(user);//更新数据库中与 user 对象主键相匹配的那条记录
        if(!isUpdate) {
            throw new UserException(ErrorEnum.UPDATE_AVATAR_ERROR);
        }
        UpdateAvatarResponse response = new UpdateAvatarResponse();
        BeanUtils.copyProperties(user, response);

        return response;
    }

    private boolean isRegister(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        long count = this.count(queryWrapper);
        return count > 0;
    }
}





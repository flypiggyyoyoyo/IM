package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.UserBalance;
import com.flypiggyyoyoyo.im.messageservice.service.UserBalanceService;
import com.flypiggyyoyoyo.im.messageservice.mapper.UserBalanceMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user_balance(用户余额表)】的数据库操作Service实现
* @createDate 2025-03-24 16:30:57
*/
@Service
public class UserBalanceServiceImpl extends ServiceImpl<UserBalanceMapper, UserBalance>
    implements UserBalanceService{

}





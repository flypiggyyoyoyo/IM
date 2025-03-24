package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.BalanceLog;
import com.flypiggyyoyoyo.im.messageservice.service.BalanceLogService;
import com.flypiggyyoyoyo.im.messageservice.mapper.BalanceLogMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【balance_log(余额变动记录表)】的数据库操作Service实现
* @createDate 2025-03-24 16:28:28
*/
@Service
public class BalanceLogServiceImpl extends ServiceImpl<BalanceLogMapper, BalanceLog>
    implements BalanceLogService{

}





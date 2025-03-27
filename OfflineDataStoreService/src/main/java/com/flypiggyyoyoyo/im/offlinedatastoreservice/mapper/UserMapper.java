package com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper;

import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-03-27 21:31:01
* @Entity com.flypiggyyoyoyo.im.offlinedatastoreservice.model.User
*/
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

}





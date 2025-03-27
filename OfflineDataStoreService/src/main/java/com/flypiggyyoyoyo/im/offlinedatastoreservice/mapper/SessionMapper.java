package com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper;

import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.Session;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author flypiggy
* @description 针对表【session(会话表)】的数据库操作Mapper
* @createDate 2025-03-27 21:29:13
* @Entity com.flypiggyyoyoyo.im.offlinedatastoreservice.model.Session
*/
@Mapper
public interface SessionMapper extends BaseMapper<Session> {

}





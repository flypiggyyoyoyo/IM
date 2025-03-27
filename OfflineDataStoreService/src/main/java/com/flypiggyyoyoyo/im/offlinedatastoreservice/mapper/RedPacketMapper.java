package com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper;

import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.RedPacket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author flypiggy
* @description 针对表【red_packet(红包主表)】的数据库操作Mapper
* @createDate 2025-03-27 21:30:08
* @Entity com.flypiggyyoyoyo.im.offlinedatastoreservice.model.RedPacket
*/
@Mapper
public interface RedPacketMapper extends MPJBaseMapper<RedPacket> {

}





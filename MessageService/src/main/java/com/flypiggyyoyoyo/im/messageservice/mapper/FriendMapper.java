package com.flypiggyyoyoyo.im.messageservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flypiggyyoyoyo.im.messageservice.model.Friend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author flypiggy
* @description 针对表【friend(联系人表)】的数据库操作Mapper
* @createDate 2025-03-22 21:10:45
* @Entity com.flypiggyyoyoyo.im.messageservice.model.Friend
*/
public interface FriendMapper extends BaseMapper<Friend> {
    /**
     * 检查好友关系
     * @param userId 用户id
     * @param friendId 朋友id
     * @return
     */
    @Select("SELECT * FROM friend WHERE user_id = #{userId} AND friend_id = #{friendId} AND status = 1")
    Friend selectFriendship(@Param("userId") Long userId, @Param("friendId") Long friendId);
}





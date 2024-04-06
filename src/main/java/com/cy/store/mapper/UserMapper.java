package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    Integer insert(User user);

    User findByUsername(String username);

    User findByUid(Integer uid);


    /**
     *  根据uid修改用户的密码
     * @param uid 被修改的用户uid
     * @param password 新密码
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime")Date modifiedTime);
}

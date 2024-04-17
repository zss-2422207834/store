package com.cy.store.service;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {
    void reg(User user);
    User login (String username,String password);


    /**
     *
     * @param uid  被修改这uid
     * @param username 修改人的用户名 即修改者
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid,String username,
                        String oldPassword,String newPassword);
    User getByUid(Integer uid);

    /**
     * 更改用户信息
     * @param ("username")  用户名
     * @param ("uid") 控制层从session中获取的uid 谁登录改谁的信息 而前端表单提供username
     *              ，phone，email，gender没有uid所以需要从session获取
     * @param user  封装着电话号，邮箱性别的对象
     */
    /*void changeInfo(Integer uid,String username,User user);*/
    void changeInfo(User user);

    void changeAvatar(Integer uid,
                      String avatar,
                      String modifiedUser);
}

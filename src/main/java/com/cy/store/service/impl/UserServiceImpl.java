package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public void reg(User user) {
        String name = user.getUsername();

        User result = userMapper.findByUsername("name");

        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }

        String oldpwd = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5pwd = getMd5Passwd(oldpwd, salt);

        user.setPassword(md5pwd);
        user.setSalt(salt);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);


        Integer rows = userMapper.insert(user);

        if (rows != 1) {
            throw new InsertException("插入异常");
        }


    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UsernameNotFindException("用户名不存在");
        }

        if (result.getIsDelete() == 1 || result.getIsDelete() == null) {
            throw new UsernameNotFindException("用户名不存在");
        }
        String salt = result.getSalt();
        String userpwd = getMd5Passwd(password, salt);
        String dbpwd = result.getPassword();
        if (!userpwd.equals(dbpwd)) {
            throw new PasswordNotMatchException("您输入的密码有误");
        }
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFindException("用户名不存在");
        }
        String oldMd5Password = getMd5Passwd(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("您输入的密码有误");
        }
        String newMd5Passwd = getMd5Passwd(newPassword, result.getSalt());

        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Passwd, username, new Date());

        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }


    }

    @Override
    public User getByUid(Integer uid) {

        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFindException("用户名不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;
    }


    @Override
    public void changeInfo(User user) {
        User result = userMapper.findByUid(user.getUid());
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFindException("用户名不存在");
        }
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }

    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result=userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UsernameNotFindException("用户不存在或者已经被删除");
        }
        Integer row=userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (row !=1){
            throw new UpdateException("更新时产生未知异常");
        }
    }


    public String getMd5Passwd(String pwd, String salt) {

        for (int i = 0; i < 3; i++) {
            pwd = DigestUtils.md5DigestAsHex((salt + pwd + salt).getBytes()).toUpperCase();
        }
        return pwd;
    }

}

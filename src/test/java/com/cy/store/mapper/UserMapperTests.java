package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void insert() {
        User user = new User();
        user.setUsername("quiet1");
        user.setPassword("quiet1");
        Integer row = userMapper.insert(user);
        System.out.println(row);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("lisi");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(2, "1234", "zss", new Date());
    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(2));
    }

    @Test
    public void updateInfoByUid(){
        User user= userMapper.findByUid(2);
        user.setEmail("2422207834@qq.com");
        user.setPhone("15633562772");
        user.setGender(1);
        user.setModifiedUser("zss");
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(5,"/root/09.jpg","zhangsan",new Date());
    }

}

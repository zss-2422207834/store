package com.cy.store.service;


import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UsernameNotFindException;
import com.cy.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.ServerException;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void  reg(){
        try {
            User user =new User();
            user.setUsername("quiet123");
            user.setPassword("quiet");
            iUserService.reg(user);
            System.out.println("Ok");
        } catch (ServiceException e) {

            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void updatePasswordByUid(){
        User user =new User();
        user.setUsername("quiet009");
        user.setPassword("quiet");
    }
    @Test
    public void changePassword(){
        iUserService.changePassword(3,"quiet009","quiet","quiet");
    }

    @Test
    public void getByUid() {

        User user = iUserService.getByUid(4);
        System.out.println(user);

    }

    @Test
    public void changeInfo() {
        User user=new User();
        user.setUid(5);
        user.setModifiedUser("test001");
        user.setPhone("19955815655");
        user.setEmail("746035971@qq.com");
        user.setGender(0);
        iUserService.changeInfo(user);
    }
    @Test
    public void changeAvatar(){
        iUserService.changeAvatar(5,"/root/08.rpg","lisi");
    }
}

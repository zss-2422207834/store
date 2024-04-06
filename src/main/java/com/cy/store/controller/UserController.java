package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /*@RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        JsonResult<Void> result = new JsonResult<>();

        try {
            UserService.reg(user);
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("插入操作异常");
        }

        return result;
    }*/
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(200);
    }


    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession){
        User data=userService.login(username,password);
        httpSession.setAttribute("uid",data.getUid());
        httpSession.setAttribute("username",data.getUsername());
        /*System.out.println(getusernameFromSession(httpSession));
        System.out.println(getuidFromSession(httpSession));*/
        return new JsonResult<User>(200,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username=getusernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(ok);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data=userService.getByUid(getuidFromSession(session));

        return new JsonResult<>(ok,data);
    }
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){

        user.setUid(getuidFromSession(session));
        user.setModifiedUser(getusernameFromSession(session));
        userService.changeInfo(user);
        return new JsonResult<>(ok);
    }



    //设置文件的最大值
    public  static final int AVATAR_MAX_SIZE=10*1024*1024;

    /*设置上传文件的类型和*/
    public  static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/ bmp");
        AVATAR_TYPE.add("image/gif");

    }


    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件过大");
        }
        String contentType= file.getContentType();

        System.out.println(contentType);
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型为："+contentType+",不符合文件类型");
        }

        String parent=session.getServletContext().getRealPath("upload");
        File dir=new File(parent);
        if (!dir.exists()){
            dir.mkdirs();
        }
        String originalFilename=file.getOriginalFilename();
        System.out.println("originalFilename"+originalFilename);


        int index =originalFilename.lastIndexOf(".");
        String filename= UUID.randomUUID().toString().toUpperCase()+originalFilename.substring(index);


        File dest=new File(dir,filename);

        try {
            file.transferTo(dest);
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        catch (IOException e) {
            throw new FileUploadException("文见上传异常");
        }

        String avatar="/upload/"+filename;
        userService.changeAvatar(getuidFromSession(session),avatar,getusernameFromSession(session));

        return new JsonResult<>(200,avatar);
    }
}

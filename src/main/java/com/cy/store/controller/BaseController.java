package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int ok=200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用了");
        }else if(e instanceof UsernameNotFindException){
            result.setState(4001);
            result.setMessage("用户名不存在");
        } else if(e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("您输入的密码有误");
        }  else if(e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("地址超出限制");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生为止异常");
        }else if(e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据产生未知的异常");
        }else if(e instanceof FileEmptyException){
            result.setState(6001);
            result.setMessage("文件为空");
        }else if(e instanceof FileSizeException){
            result.setState(6002);
            result.setMessage("文件大小超出限制");
        }else if(e instanceof FileUploadIOException){
            result.setState(6003);
            result.setMessage("文件传输出现问题，请稍后重试");
        }else if(e instanceof FileTypeException){
            result.setState(6004);
            result.setMessage("文件类型不符合");
        }else if(e instanceof FileStateException){
            result.setState(6002);
            result.setMessage("文件状态有误");
        }

        return result;
    }

    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final  String getusernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}

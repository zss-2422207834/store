package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService iAddressService;


    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address,HttpSession httpSession){
        Integer uid =getuidFromSession(httpSession);
        String username=getusernameFromSession(httpSession);
        iAddressService.addNewAddress(uid,username,address);


        return new JsonResult<>(200);

    }











}

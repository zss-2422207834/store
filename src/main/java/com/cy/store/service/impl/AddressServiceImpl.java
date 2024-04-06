package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer i=addressMapper.countByUid(uid);
        if(i>=maxCount){
            throw new AddressCountLimitException("收货地址超过"+maxCount);
        }
        if(i==0){
            address.setIsDefault(1);
        }
        address.setUid(uid);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer row=addressMapper.insert(address);

        if (row!=1){
            throw new InsertException("插入数据产生未知异常");

        }
    }
}

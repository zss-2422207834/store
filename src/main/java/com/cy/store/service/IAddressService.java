package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;

public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);
}

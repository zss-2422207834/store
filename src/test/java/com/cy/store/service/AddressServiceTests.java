package com.cy.store.service;


import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setPhone("13455677890");
        address.setName("王五");
        iAddressService.addNewAddress(11,"王五",address);
    }


}

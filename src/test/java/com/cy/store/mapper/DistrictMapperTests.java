package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {

    @Autowired
    DistrictMapper districtMapper;

    @Test
    public void findByParentCode(){

        List<District> row=districtMapper.findByParentCode("650000");
        for(District d:row){
            System.out.println(d);

        }
    }
}

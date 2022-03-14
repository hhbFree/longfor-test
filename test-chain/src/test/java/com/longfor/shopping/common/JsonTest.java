package com.longfor.shopping.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonTest {

    @Test
    public void test(){
        String jsonS="[\n" +
                "    {\n" +
                "      \"roleCode\": \"R_UC_123033\",\n" +
                "      \"roleName\": \"地区物业装饰采购中心专员\",\n" +
                "      \"roleLabelName\": \"C4-装饰-供应链-采购-员工\",\n" +
                "      \"roleType\": \"1\",\n" +
                "      \"orgList\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"roleCode\": \"R_AM_03817\",\n" +
                "      \"roleName\": \"塘鹅美装修供应链招标采购员工\",\n" +
                "      \"roleLabelName\": \"C6-总部-供应链-招标采购-员工\",\n" +
                "      \"roleType\": \"1\",\n" +
                "      \"orgList\": []\n" +
                "    }\n" +
                "  ]";

        List<RoleOrgDto> roleOrgDtos = JSONArray.parseArray(jsonS, RoleOrgDto.class);
        Set<RoleOrgDto> collect = roleOrgDtos.stream().collect(Collectors.toSet());
        System.out.println(collect.toString());
    }



}

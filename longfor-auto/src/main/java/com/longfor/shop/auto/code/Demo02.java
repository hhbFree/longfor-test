package com.longfor.shop.auto.code;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo02 {
    public static void main(String[] args) {
      String s="{\n" +
              "  \"respCode\": {\n" +
              "    \"code\": \"1\",\n" +
              "    \"message\": \"success\"\n" +
              "  },\n" +
              "  \"data\": [\n" +
              "    {\n" +
              "      \"roleCode\": \"R_AM_03839\",\n" +
              "      \"roleName\": \"塘鹅美装修事业部员工\",\n" +
              "      \"roleLabelName\": \"C6-事业部-员工\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": []\n" +
              "    },\n" +
              "    {\n" +
              "      \"roleCode\": \"R_AM_03197\",\n" +
              "      \"roleName\": \"地区物业装饰片区供应链员工\",\n" +
              "      \"roleLabelName\": \"C4-装饰片区-供应链-员工\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": []\n" +
              "    },\n" +
              "    {\n" +
              "      \"roleCode\": \"R_AM_04924\",\n" +
              "      \"roleName\": \"塘鹅美装修运营专员\",\n" +
              "      \"roleLabelName\": \"C6-总部-工程-运营专员\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": [\n" +
              "        {\n" +
              "          \"code\": \"6000164515\",\n" +
              "          \"name\": \"塘鹅美装修总部\",\n" +
              "          \"namePath\": \"GROUP/龙湖集团/塘鹅美装修/塘鹅美装修总部\",\n" +
              "          \"typeCode\": \"02\",\n" +
              "          \"productGroup\": \"C6\"\n" +
              "        },\n" +
              "        {\n" +
              "          \"code\": \"6000136875\",\n" +
              "          \"name\": \"运营工程部\",\n" +
              "          \"namePath\": \"GROUP/龙湖集团/塘鹅美装修/塘鹅美装修总部/运营工程部\",\n" +
              "          \"typeCode\": \"02\",\n" +
              "          \"productGroup\": \"C6\"\n" +
              "        }\n" +
              "      ]\n" +
              "    },\n" +
              "    {\n" +
              "      \"roleCode\": \"R_AM_03252\",\n" +
              "      \"roleName\": \"地区物业装饰片区运营员工\",\n" +
              "      \"roleLabelName\": \"C4-装饰片区-运营-员工\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": []\n" +
              "    },\n" +
              "    {\n" +
              "      \"roleCode\": \"R_AM_04902\",\n" +
              "      \"roleName\": \"塘鹅美装修区域供应链采购员工\",\n" +
              "      \"roleLabelName\": \"C6-区域-供应链-采购员工\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": []\n" +
              "    },\n" +
              "    {\n" +
              "      \"roleCode\": \"R_UC_123013\",\n" +
              "      \"roleName\": \"地区物业装饰片区供应链管理采购专员\",\n" +
              "      \"roleLabelName\": \"C4-装饰片区-供应链-采购专员\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": []\n" +
              "    },\n" +
              "    {\n" +
              "      \"roleCode\": \"R_AM_04884\",\n" +
              "      \"roleName\": \"塘鹅美装修区域供应链员工\",\n" +
              "      \"roleLabelName\": \"C6-区域-供应链-员工\",\n" +
              "      \"roleType\": \"1\",\n" +
              "      \"orgList\": []\n" +
              "    }\n" +
              "  ]\n" +
              "}";

        JSONObject jsonObject = JSONObject.parseObject(s);

        JSONArray data = jsonObject.getJSONArray("data");


    }
}

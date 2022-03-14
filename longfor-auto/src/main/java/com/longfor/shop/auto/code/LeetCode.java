package com.longfor.shop.auto.code;

public class LeetCode {

    public static void main(String[] args) {

        String sql = "UC_GUID UC GUID CHAR 100 V1.1新增\n" +

                "VERSIONIF 组架版本ID CHAR 20 X\n" +


                "ORG_ID 组织ID CHAR 20 X\n" +

                "ORG_TYPE 组织形态 CHAR 20\n" +

                "ORG_NM 组织名称 STRING\n" +

                "ORG_SN 组织简称 STRING\n" +

                "DEPTY_ID 部门类型ID CHAR 20\n" +

                "DEPTY_DS 部门类型描述 STRING\n" +

                "DEPCL_ID 部门类别ID CHAR 20\n" +

                "DEPCL_DS 部门类别描述 STRING\n" +

                "PARENTID 父节点ID CHAR 20\n" +

                "COMP_ID 所属公司 CHAR 20\n" +

                "COMP_DS 公司描述 STRING\n" +

                "ACT_DATE 生效日期 CHAR 20\n" +

                "STATUS 状态 CHAR 6 A有效 I无效\n" +

                "ORGLB_ID 组织标签ID CHAR 20\n" +

                "ORGLB_DS 组织标签描述 STRING\n" +

                "ORG_HRC 组织层级 CHAR 20\n" +

                "ORG_DIRCT 组织负责人 CHAR 100\n" +

                "ORG_RANK 组织排序 CHAR 10\n" +

                "CITY 城市 CHAR 50\n" +

                "COST_CT 成本中心 CHAR 20\n" +

                "BUS_ATT 业务属性 CHAR 100 V1.1废弃\n" +

                "TAKEO_TY 接管类型 CHAR 50\n" +

                "PROP_FORM 物业业态 CHAR 100\n" +

                "CREATEDAT 创建时间 CHAR 20\n" +

                "CREATEBY 创建人OA账号 CHAR 70\n" +

                "UPDATEDAT 修改时间 CHAR 20\n" +

                "UPDATEBY 修改人OA账号 CHAR 70\n" +

                "ORG_HRCDS 组织层级描述 CHAR 100 V1.1新增\n" +

                "ORGDIRCTD 组织负责人名称 CHAR 100 V1.1新增\n" +

                "CITY_DS 城市描述 CHAR 100 V1.1新增\n" +

                "COST_CTDS 成本中心描述 STRING V1.1新增\n" +

                "TAKEOTYDS 接管类型描述 CHAR 100 V1.1新增\n" +

                "PROPFORMD 物业业态描述 CHAR 100 V1.1新增\n" +


                "PARENT_DS 父节点描述 CHAR 100 V1.1新增\n" +

                "IF_L_SHOW 龙信是否显示 CHAR 5 V1.2新增\n" +

                "INV_ATTR 投资属性 CHAR 10 V1.3新增\n" +

                "INVATTRNM 投资属性名称 CHAR 50 V1.3新增\n" +

                "POSCODEUP 织负责人岗位编码 CHAR 255 会有多值情况，用逗号分开\n" +

                "SYSFLAG 系统标识 CHAR 20 1.6新增\n" +

                "CHANNEL 航道代码 CHAR 50\n" +

                "CHANNELNM 航道名称 CHAR 50\n" +
                "PRJSTATUS 组织状态标识 CHAR 5 A:生效 I:失效";

//        +
//
//                "1:N prjList 项目信息 V1.2新增\n" +
//
//                "PROJECT MDM项目（项目身份证） CHAR 50 V1.2放入子节点\n" +
//
//                "PROJECTDS MDM项目名称 CHAR 100 V1.2放入子节点\n" +
//
//                "PRJSTATUS 组织状态标识 CHAR 5 A:生效 I:失效"

        String[] arr = sql.split("\n");
        for (String s : arr) {
            System.out.println(s.split(" ")[0]);
            ;
        }


        String sql2 = "uc_guid\n" +
                "versionif\n" +
                "org_id\n" +
                "org_type\n" +
                "org_nm\n" +
                "org_sn\n" +
                "depty_id\n" +
                "depty_ds\n" +
                "depcl_id\n" +
                "depcl_ds\n" +
                "parentid\n" +
                "comp_id\n" +
                "comp_ds\n" +
                "act_date\n" +
                "status\n" +
                "orglb_id\n" +
                "orglb_ds\n" +
                "org_hrc\n" +
                "org_dirct\n" +
                "org_rank\n" +
                "city\n" +
                "cost_ct\n" +
                "bus_att\n" +
                "takeo_ty\n" +
                "prop_form\n" +
                "createdat\n" +
                "createby\n" +
                "updatedat\n" +
                "updateby\n" +
                "org_hrcds\n" +
                "orgdirctd\n" +
                "city_ds\n" +
                "cost_ctds\n" +
                "takeotyds\n" +
                "propformd\n" +

                "parent_ds\n" +
                "if_l_show\n" +
                "inv_attr\n" +
                "invattrnm\n" +
                "poscodeup\n" +
                "sysflag\n" +
                "channel\n" +
                "channelnm\n" +
                "prjstatus";
        System.out.println(sql2);

        String[] arr2 = sql2.split("\n");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println("/*" + arr[i].split(" ")[1] + "*/");
            System.out.println("private String " + Tool.lineToHump(arr2[i]) + ";");
            System.out.println();
        }

        String head = "CREATE TABLE `mdm_md056b` (\n" +
                "  `id` varchar(50) NOT NULL COMMENT 'id',\n";
        System.out.println(head);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println("  `" + arr2[i] + "` varchar(50) DEFAULT NULL COMMENT '" + arr[i].split(" ")[1] + "',\n");
        }
        String tail = "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mdm_md056b';";
        ;

        System.out.println(tail);

        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("<if test='item." + Tool.lineToHump(arr2[i]) + "!= null'>");
            System.out.println(arr2[i] + ",");
//            System.out.println(arr[i]+",");
            System.out.println("</if>");
            System.out.println();
        }

        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("<if test='item." + Tool.lineToHump(arr2[i]) + "!= null'>");
            System.out.println("#{item." + Tool.lineToHump(arr2[i]) + ",jdbcType=VARCHAR},");
//            System.out.println(arr[i]+",");
            System.out.println("</if>");
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("<if test='item." + Tool.lineToHump(arr2[i]) + "!= null'>");
            System.out.println(arr2[i] + " = #{item." + Tool.lineToHump(arr2[i]) + ",jdbcType=VARCHAR},");
//            System.out.println(arr[i]+",");
            System.out.println("</if>");
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("<result column=\""+arr2[i]+"\" jdbcType=\"VARCHAR\" property=\""+Tool.lineToHump(arr2[i])+"\"/>");

            System.out.println();
        }


        String sql5="id,\n" +
                "code,\n" +
                "`name`,\n" +
                "parent_id,\n" +
                "type_code,\n" +
                "biz_code,\n" +
                "uc_id,\n" +
                "product_group,\n" +
                "simple_spelling,\n" +
                "create_date,\n" +
                "update_date,\n" +
                "ACTIVE_FLAG";
        String[] arr3 = sql5.split(",\n");

        for (int i = 0; i < arr3.length; i++) {
            System.out.println("<if test='" + Tool.lineToHump(arr3[i]) + "!= null'>");
            System.out.println(arr3[i]+ " = #{" + Tool.lineToHump(arr3[i]) + ",jdbcType=VARCHAR},");
//            System.out.println(arr[i]+",");
            System.out.println("</if>");
            System.out.println();
        }
    }


}

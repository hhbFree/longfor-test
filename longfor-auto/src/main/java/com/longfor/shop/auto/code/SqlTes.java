package com.longfor.shop.auto.code;

public class SqlTes {

    public static void main(String[] args) {
        String sql = "projectc6_id," +
                "proj_name," +
                "provin_c6," +
                "provinc6d," +
                "city_c6," +
                "city_c6d," +
                "region," +
                "regiond," +
                "comm_addr," +
                "house_no," +
                "qygs_cd," +
                "qygs_ds," +
                "busi_type," +
                "busityped," +
                "proj_sour," +
                "projsourd," +
                "rela_chan," +
                "relachand," +
                "rela_proj," +
                "proj_numb," +
                "deco_area," +
                "begin_tm," +
                "end_tm," +
                "cont_pers," +
                "cont_phon," +
                "money," +
                "proj_mana," +
                "tech_mana," +
                "safe_mana," +
                "documento," +
                "prog_stat," +
                "progstatd," +
                "cont_stat," +
                "contstatd," +
                "risk_leve," +
                "riskleved," +
                "remark_c6," +
                "active_c6," +
                "buunit_cd," +
                "buunit_nm," +
                "unit_cd," +
                "unit_nm," +
                "ac_sta_dt," +
                "ac_fin_dt," +
                "cr_dt_c6," +
                "cr_us_c6," +
                "up_dt_c6," +
                "up_us_c6";


        String sql2 = "projectc6," +
                "projName," +
                "provinC6," +
                "provinc6d," +
                "cityC6," +
                "cityC6d," +
                "region," +
                "regiond," +
                "commAddr," +
                "houseNo," +
                "qygsCd," +
                "qygsDs," +
                "busiType," +
                "busityped," +
                "projSour," +
                "projsourd," +
                "relaChan," +
                "relachand," +
                "relaProj," +
                "projNumb," +
                "decoArea," +
                "beginTm," +
                "endTm," +
                "contPers," +
                "contPhon," +
                "money," +
                "projMana," +
                "techMana," +
                "safeMana," +
                "documento," +
                "progStat," +
                "progstatd," +
                "contStat," +
                "contstatd," +
                "riskLeve," +
                "riskleved," +
                "remarkC6," +
                "activeC6," +
                "buunitCd," +
                "buunitNm," +
                "unitCd," +
                "unitNm," +
                "acStaDt," +
                "acFinDt," +
                "crDtC6," +
                "crUsC6," +
                "upDtC6," +
                "upUsC6";
        String[] arr = sql.split(",");
        String[] arr2 = sql2.split(",");

        for (int i=0;i<arr.length;i++) {
            System.out.println("<if test='item." + arr2[i] + "!= null'>");
            System.out.println(arr[i]+" = #{"+arr2[i]+",jdbcType=VARCHAR},");
//            System.out.println(arr[i]+",");
            System.out.println("</if>");
            System.out.println();
        }

    }
}
        
    


package longfor.test.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import longfor.test.excel.pojo.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ReadEx4 {


    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream4 = null;
        Map<String,Integer> map=new ConcurrentHashMap<>();
        Map<String,String> mapTree=new ConcurrentHashMap<>();
        map.put("家具采购",100064);
        mapTree.put("家具采购","0,100049");
        try {

            File file4 = new File("/Users/admin/Desktop/未命名文件夹/与物业共用同一分类.xlsx");

            fileInputStream4 = new FileInputStream(file4);

            List<Map<Integer, String>> list4 = EasyExcelFactory.read(fileInputStream4).sheet(0).headRowNumber(0).doReadSync();

            //三级分类sql
            for (int i = 1; i < list4.size(); i++) {
                System.out.println("update pd_category set state=0,is_delete='Y' where id ="+list4.get(i).get(0)+";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            fileInputStream4.close();
        }
    }
}

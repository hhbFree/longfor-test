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

public class ReadEx2 {


    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4 = null;
        Map<String,Integer> map=new ConcurrentHashMap<>();
        map.put("礼品物料",101822);
        try {
            Set<Category> set=new HashSet<>();

            File file = new File("/Users/admin/Desktop/未命名文件夹/二三级直接并入，二级改名/二三级直接并入，二级改名.xlsx");
//            File file2 = new File("/Users/admin/Desktop/未命名文件夹/二三级直接并入，二级改名/one.xlsx");
//            File file3 = new File("/Users/admin/Desktop/未命名文件夹/二三级直接并入，二级改名/要修改的二级分类.xlsx");
            File file4 = new File("/Users/admin/Desktop/未命名文件夹/二三级直接并入，二级改名/行政二级三级直接并入，二级改名.xlsx");
            fileInputStream = new FileInputStream(file);
//            fileInputStream2 = new FileInputStream(file2);
//            fileInputStream3 = new FileInputStream(file3);
            fileInputStream4 = new FileInputStream(file4);
            List<Map<Integer, String>> list = EasyExcelFactory.read(fileInputStream).sheet(0).headRowNumber(0).doReadSync();
//            List<Map<Integer, String>> list2 = EasyExcelFactory.read(fileInputStream2).sheet(0).headRowNumber(0).doReadSync();
//            List<Map<Integer, String>> list3 = EasyExcelFactory.read(fileInputStream3).sheet(0).headRowNumber(0).doReadSync();
            List<Map<Integer, String>> list4 = EasyExcelFactory.read(fileInputStream4).sheet(0).headRowNumber(0).doReadSync();

            //一级分类对应的 name、id
//            for (int i = 1; i < list2.size(); i++) {
//                map.put(list2.get(i).get(4)+"",Integer.parseInt(list2.get(i).get(0)+""));
//            }

            //二级分类对应修改后一级分类的集合
            for (int i = 1; i < list.size(); i++) {
                Category category=new Category();
                category.setName(list.get(i).get(1)+"");
                category.setId(Integer.parseInt(list.get(i).get(3)+""));
                category.setParentName(list.get(i).get(0));
                category.setParentId(map.get(list.get(i).get(0)));
                set.add(category);
            }

            //要修改的二级分类id
            System.out.println(JSON.toJSONString(set));
//            for (Category category : set) {
//                System.out.println("'"+category.getId()+"',");
//            }

            Map<Integer, Category> collect = set.stream().collect(Collectors.toMap(Category::getId, p -> p));

            //二级分类sql
//            for (Category category : set) {
//                System.out.println("update pd_category set sys=3,parent_id="+collect.get(category.getId()).getParentId()+",tree_path='0,"+collect.get(category.getId()).getParentId()+"' where id="+category.getId()+";");
//            }

//
            //三级分类sql
            for (int i = 1; i < list4.size(); i++) {
                String[] split = list4.get(i).get(8).split(",");
                System.out.println("update pd_category set sys=3,tree_path='0,"+collect.get(Integer.parseInt(split[2])).getParentId()+","+split[2]+"' where id="+list4.get(i).get(0)+";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
//            fileInputStream2.close();
//            fileInputStream3.close();
            fileInputStream4.close();
        }
    }
}

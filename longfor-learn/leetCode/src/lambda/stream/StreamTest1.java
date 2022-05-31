package lambda.stream;

import lambda.test1.Employee;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest1 {
    //中间操作
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9496.2),
            new Employee("李四", 52, 2396.2),
            new Employee("王五", 56, 996.2),
            new Employee("赵六", 8, 94.2)
    );
    /*  筛选与切片
     *  filter--接收Lambda，从流中排除某些元素。
     *  limit--截断流，使其元素不超过给定数量。
     *  skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n) 互补
     *  distinct--筛选，通过流所生成元素的 hashCode() 和 equals() 去掉重复元素
     */

    //内部迭代：迭代操作由 Stream API 完成
    @Test
    public void test1() {
        List<Employee> employees2 = new ArrayList<>();
        employees.stream().filter((x) -> x.getSalary() > 100).forEach(System.out::println);
        Stream<Employee> employeeStream = employees.stream().filter((x) -> x.getSalary() > 100);
        System.out.println("-----------------------------------------");
        Iterator<Employee> iterator = employeeStream.iterator();
        while (iterator.hasNext()) {
            employees2.add(iterator.next());
        }
        System.out.println(employees2.toString());
    }

    /**
     * limit/filter/distinct/skip
     */
    @Test
    public void test2() {
        employees.stream()
                .limit(2)//只差前两个
                .filter((x) -> x.getSalary() > 500)
                .skip(1)//跳过第一个
                .distinct()//去重 注意：需要Employee重写hashCode 和 equals 方法
                .forEach(System.out::println);
    }

    /**
     * map映射
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream().map((x)->x.toUpperCase()).forEach(System.out::println);
        System.out.println("------------------------------");
        employees.stream().map(Employee::getSalary).forEach(System.out::println);
        System.out.println("------------------------------");
        Stream<Character> characterStream = list.stream().flatMap(StreamTest1::filterCharacter);
        characterStream.forEach(System.out::println);
    }


    public static Stream<Character> filterCharacter(String str){
        List<Character> characters=new ArrayList<>();
        for (Character character : str.toCharArray()) {
            characters.add(character);
        }
        return characters.stream();
    }

    @Test
    public void test4(){//map和flatMap的关系  类似于 add(Object)和addAll(Collection coll)
        List<String> list=Arrays.asList("aaa","bbb","ccc","ddd");
        List list2=new ArrayList<>();
        list2.add(11);
        list2.add(22);
        list2.addAll(list);
        System.out.println(list2);
    }


    /**
     * 排序
     * 先根据age比较，age相同时再根据工资比较
     */
    @Test
    public void test5(){
        employees.stream().sorted((e1,e2)->{
            if (e1.getAge().equals(e2.getAge())){
                return e1.getSalary().compareTo(e2.getSalary());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }



}

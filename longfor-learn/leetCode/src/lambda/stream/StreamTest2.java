package lambda.stream;

import lambda.test1.Employee;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest2 {

    /**
     * Function<T,R> :函数型接口
     * R apply(T t);
     * <p>
     */
    @Test
    public void test11() {
        String s = strHandler(" lamda", (x) -> x.trim());
        System.out.println(s);
    }

    //处理字符串
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * Predicate<T> :断言型接口
     * boolean test(T t);
     */
    @Test
    public void test12() {
        List<String> strings = Arrays.asList("1", "2132", "333");
        List<String> strings1 = filterStr(strings, (x) -> x.length() > 2);
        System.out.println(strings1);

    }

    /**
     * 判断集合中数据是否符合条件
     *
     * @param strList
     * @param predicate
     * @return
     */
    public List<String> filterStr(List<String> strList, Predicate<String> predicate) {
        List<String> list = new ArrayList<>(8);
        for (String s : strList) {
            if (predicate.test(s)) {
                list.add(s);
            }
        }
        return list;
    }


    @Test
    public void test1() {
        LocalDate now = LocalDate.now();
        LocalDate preTwoDate = now.minusMonths(2);
        LocalDate preOneDate = now.minusMonths(1);
        LocalDate firstDay = LocalDate.of(preTwoDate.getYear(), preTwoDate.getMonth(), 1);
        LocalDate lastDay = preOneDate.with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dateTimeFormatter.format(firstDay));
        System.out.println(dateTimeFormatter.format(lastDay));

    }

    @Test
    public void test2() {
        Date updateTime = new Date();
        Instant instant = updateTime.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime start = instant.atZone(zoneId).toLocalDateTime();
        Date updateTime2 = new Date();
        Instant instant2 = updateTime2.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId2 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime start2 = instant2.atZone(zoneId2).toLocalDateTime();
        long hours = Duration.between(start, start2).toMillis();
    }

    /**
     * 匹配
     */
    @Test
    public void test3() {
        //1.匹配所有
        boolean b = employees.stream().allMatch((e) -> e.getState().equals(Employee.State.a));
        System.out.println(b);
        //2.至少存在一个
        boolean b1 = employees.stream().anyMatch((e) -> e.getState().equals(Employee.State.a));
        System.out.println(b1);
        //3.一个都没有
        boolean b2 = employees.stream().noneMatch((e) -> e.getState().equals(Employee.State.c));
        System.out.println(b2);
        //4.返回工资最大的第一个元素
        Optional<Employee> first = employees.stream().sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(first.get());
        //5.返回流中最大属性值
        Optional<Double> max = employees.stream().map(Employee::getSalary).max(Double::compareTo);
        System.out.println(max.get());
        //6.返回流中的最小值
        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min.get());
    }

    /**
     * 归约
     */
    @Test
    public void test4() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = integers.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Optional<Double> reduce1 = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce1.get());
    }

    /**
     * 收集
     */
    @Test
    public void test5() {
        //1.收集成一个list集合
        List<String> collect = employees.stream().map(Employee::getName).collect(Collectors.toList());
        collect.forEach(System.out::println);

        //2.收集成一个set集合
        Set<Integer> collect1 = employees.stream().map(Employee::getAge).collect(Collectors.toSet());
        collect1.forEach(System.out::println);

        //3.收集成一个hash集合
        HashSet<Double> collect2 = employees.stream().map(Employee::getSalary).collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);

        //4.求和 最大值，最小值
        Optional<Double> reduce = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce.get());

        DoubleSummaryStatistics collect3 = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect3.getSum());
        System.out.println(collect3.getMax());
        Optional<Double> max = employees.stream().map(Employee::getSalary).max(Double::compareTo);
        Optional<Employee> min = employees.stream().min((e, x) -> Double.compare(e.getSalary(), x.getSalary()));

        //5.分组
        //根据state分组
        Map<Employee.State, List<Employee>> collect4 = employees.stream().collect(Collectors.groupingBy(Employee::getState));
        System.out.println(collect4);

        //多级分组
        Map<Employee.State, Map<String, List<Employee>>> collect5 = employees.stream().collect(Collectors.groupingBy(Employee::getState, Collectors.groupingBy((x) -> {
            if (x.getAge() > 20) {
                return "青年";
            }
            return "老年";
        })));

        System.out.println(collect5);
    }

    //中间操作
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9496.2, Employee.State.a),
            new Employee("李四", 52, 2396.2, Employee.State.b),
            new Employee("王五", 56, 996.2, Employee.State.b),
            new Employee("赵六", 8, 94.2, Employee.State.d)
    );
}

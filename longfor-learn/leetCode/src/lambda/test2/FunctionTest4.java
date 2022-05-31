package lambda.test2;


import lambda.test1.Employee;
import org.junit.Test;


import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用时Lambda表达式的另一种表现形式）
 * <p>
 * 主要有三种语法格式：
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * <p>
 * 注意：
 * 1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 * 2、若Lambda参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 * <p>
 * <p>
 * 二、构造器引用:
 * 格式：
 * ClassName::new
 * <p>
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 * <p>
 * <p>
 * 三：数组引用：
 * Type::new;
 */
public class FunctionTest4 {
    /**
     * 实例方法名
     */
    @Test
    public void test1() {
        PrintStream printStream = System.out;
        Consumer<String> consumer = printStream::println;
        consumer.accept("aaa");

        PrintStream printStream2 = System.out;
        Consumer<String> consumer1 = (x) -> printStream2.println(x);
        consumer1.accept("2222");

    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> stringSupplier = employee::getName;
        stringSupplier.get();
        Supplier<String> runnable = () -> employee.getName();
        runnable.get();
    }

    @Test
    public void test3() {
        Comparator<Integer> com = Integer::compareTo;
        int compare = com.compare(1, 2);
        System.out.println(compare);
    }


    //类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
    }


    //构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();

        //构造器引用方式
        Supplier<Employee> sup2 = Employee::new;//使用无参构造器
        Employee emp = sup2.get();
        System.out.println(emp);


    }

    //数组引用
    @Test
    public void test6() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] str2 = fun2.apply(20);
        System.out.println(str2.length);
    }


}

package lambda.test2;



import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Java8中引入了一个新的操作符”->” 该操作符称为箭头操作符或Lambda操作符，箭头操作符将Lambda表达式拆分成两部分：
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能，即 Lambda 体
 */
public class FunctionTest {

    //语法格式一：无参数，无返回值
    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("普通方式");
            }
        };
        r.run();

        Runnable rr = () -> System.out.println("lambda");

        rr.run();
    }

    //语法格式二：有一个参数，并且无返回值
    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("有一个参数，并且无返回值");
    }

    //语法格式三：若只有一个参数，小括号可以不写
    @Test
    public void test3() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("若只有一个参数，小括号可以不写");
    }

    //语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
    @Test
    public void test4(){
        Comparator<Integer> com=(x,y)->{
            System.out.println("1111");
            return Integer.compare(x,y);
        };
        int compare = com.compare(1, 2);
        System.out.println(compare);
    }

    //语法格式五：若Lambda体中只有一条语句，大括号和 return 都可以省略不写
    @Test
    public void test5(){
        Comparator<Integer> comparator=(x,y)->Integer.compare(x,y);
        int compare = comparator.compare(1, 111);
        System.out.println(compare);
    }

    //语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
    @Test
    public void test6(){
//        左右遇一括号省
//        左侧推断类型省
    }
}

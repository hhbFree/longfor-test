package lambda.test2;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java内置四大函数式接口
 * <p>
 * Consumer<T> :消费型接口
 * void accept(T t);
 * <p>
 * Supplier<T> :供给型接口
 * T get();
 * <p>
 * Function<T,R> :函数型接口
 * R apply(T t);
 * <p>
 * Predicate<T> :断言型接口
 * boolean test(T t);
 */

public class FunctionTest3 {

    @Test
    public void test1() {
        con(100.0, (x) -> System.out.println(x));
    }

    public void con(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test2() {
        List<Double> numList = getNumList(10, () -> Math.random() * 100);
        System.out.println(numList.toString());
    }

    public List<Double> getNumList(Integer num, Supplier<Double> supplier) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Function<T,R> :函数型接口
     * R apply(T t);
     * <p>
     */
    @Test
    public void test3() {
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
    public void test4() {
        List<String> strings = Arrays.asList("1", "2132", "333");
        List<String> strings1 = filterStr(strings, (x) -> x.length() > 2);
        System.out.println(strings1);

    }

    /**
     * 判断集合中数据是否符合条件
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

}

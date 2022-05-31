package lambda.test2;

import lambda.test1.Employee;
import org.junit.Test;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionTest2 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9496.2),
            new Employee("李四", 52, 2396.2),
            new Employee("王五", 56, 996.2),
            new Employee("赵六", 8, 94.2)
    );


    @Test
    public void test1() {
        Integer operation = operation(100, (x) -> x * x);
        System.out.println(operation);
    }

    public Integer operation(Integer num, MyFunction myFunction) {
        return myFunction.getValue(num);
    }


    @Test
    public void test2() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e2.getName().compareTo(e1.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
    }
}

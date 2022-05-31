package reflex;

/**
 * 当我们new一个新对象或者引用静态成员变量时，Java虚拟机(JVM)中的类加载器子系统会将对应Class对象加载到JVM中，
 * 然后JVM再根据这个类型信息相关的Class对象创建我们需要实例对象或者提供静态变量的引用值。需要特别注意的是，
 * 手动编写的每个class类，无论创建多少个实例对象，在JVM中都只有一个Class对象，
 * ————————————————
 * 版权声明：本文为CSDN博主「zejian_」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/javazejian/article/details/70768369
 */
class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }

    public Gum() {
        System.out.println("构造函数加载");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }
}

public class SweetShop {
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        print("inside main");
        new Candy();
        print("After creating Candy");
        try {
            Class.forName("reflex.Gum");
            Class.forName("reflex.Gum");
            new Gum();
            new Gum();
        } catch (ClassNotFoundException e) {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"reflex.Gum\")");
        new Cookie();
        print("After creating Cookie");
        Class<Boolean> type = Boolean.TYPE;


    }
}

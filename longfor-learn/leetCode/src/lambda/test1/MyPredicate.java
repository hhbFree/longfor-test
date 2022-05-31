package lambda.test1;
@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);
}
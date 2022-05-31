package condition.one;

public class one_test {

    public static void main(String[] args) {
        Deal deal=new Deal();
        customer_one customer_one=new customer_one(deal);

        product_one product_one=new product_one(deal);

        Thread thread=new Thread(customer_one);

        Thread thread1=new Thread(product_one);

        thread.start();
        thread1.start();
    }
}

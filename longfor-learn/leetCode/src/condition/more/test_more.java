package condition.more;

public class test_more {
    public static void main(String[] args) {
        Deal_More deal_more=new Deal_More();
        customer_more customer_more=new customer_more(deal_more);

        product_more product_more=new product_more(deal_more);

        Thread thread=new Thread(customer_more);

        Thread thread1=new Thread(product_more);

        thread.start();
        thread1.start();

    }
}

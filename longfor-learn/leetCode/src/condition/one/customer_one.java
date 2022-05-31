package condition.one;

public class customer_one implements Runnable {
    private Deal deal;

    public customer_one(Deal deal) {
        this.deal = deal;
    }

    @Override
    public void run() {
        while (true){
            deal.consume();
        }
    }
}

package condition.one;

public class product_one implements Runnable {
    private Deal deal;

    public product_one(Deal deal) {
        this.deal = deal;
    }

    @Override
    public void run() {
        while (true){
            deal.product("北京烤鸭");
        }
    }
}

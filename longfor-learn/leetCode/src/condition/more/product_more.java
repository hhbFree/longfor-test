package condition.more;

public class product_more implements Runnable {
    Deal_More deal_more;

    public product_more(Deal_More deal_more) {
        this.deal_more = deal_more;
    }

    @Override
    public void run() {
        while (true){
            deal_more.product("北京烤鸭");
        }
    }
}

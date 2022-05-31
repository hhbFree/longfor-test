package condition.more;

import java.io.Serializable;

public class customer_more implements Runnable, Serializable {
    Deal_More deal_more;

    public customer_more(Deal_More deal_more) {
        this.deal_more = deal_more;
    }

    @Override
    public void run() {
        while (true){
            deal_more.consume();
        }
    }

}

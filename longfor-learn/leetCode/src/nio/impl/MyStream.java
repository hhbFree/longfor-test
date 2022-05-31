package nio.impl;

public class MyStream {
    public MyChannel getChannel() {
        synchronized (this) {

            MyChannel channel = MyChannelImple.open(null, false, true, true, this);

            return channel;
        }
    }
}

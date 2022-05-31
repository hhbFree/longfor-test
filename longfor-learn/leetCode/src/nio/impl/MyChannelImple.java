package nio.impl;


import java.io.FileDescriptor;

public class MyChannelImple extends MyChannel {

    public MyChannelImple(FileDescriptor var0, boolean var1, boolean var2, boolean var3, Object var4) {
        super();
    }

    public static MyChannel open(FileDescriptor var0, boolean var1, boolean var2, boolean var3, Object var4) {
        return new MyChannelImple(var0, var1, var2, var3, var4);
    }


    public void transferFrom(){
        System.out.println("aaaaa");
    }
}

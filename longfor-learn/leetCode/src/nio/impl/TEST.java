package nio.impl;


import org.junit.Test;

public class TEST {

    @Test
    public void test(){
        MyStream myStream=new MyStream();
        MyChannel channel = myStream.getChannel();
        channel.transferFrom();

    }
}

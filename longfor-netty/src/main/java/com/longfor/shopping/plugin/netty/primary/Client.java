package com.longfor.shopping.plugin.netty.primary;
 
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try ( //发送到8888端口
              Socket socket=new Socket("127.0.0.1", 7397);
              //输出流
              OutputStream outputStream=socket.getOutputStream();
              PrintWriter printWriter=new PrintWriter(outputStream);){

            printWriter.write(new String("server3 ,\r\n i am client \r\n".getBytes(),"UTF-8"));
            printWriter.flush();
            BufferedReader in;
            while (true){

                // 由Socket对象得到输出流，并构造PrintWriter对象
              in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              System.out.println("Server:" + in.readLine());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
    
    
    
 
}
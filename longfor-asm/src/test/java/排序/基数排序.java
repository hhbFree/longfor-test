package 排序;

import java.util.Arrays;

public class 基数排序 {
   
    public static void main(String[] args) {
        int[] arr={124,453,206};
        int[] sort = sort(arr,3);
        print(sort);
    }

    public static int[] sort(int[] arr,int size){
       int[] result=new int[arr.length];
       int[] count=new int[10];
       
       for(int j=0;j<size;j++){
            int num=(int)Math.pow(10,j);
            
            for(int i=0;i<arr.length;i++){
                int value=(arr[i]/num)%10;
              count[value]=count[value]+1;
            }
                   
            for(int i=1;i<count.length;i++){
              count[i]=count[i]+count[i-1];
            }
            
            //稳定赋值
            for(int i=arr.length-1;i>=0;i--){
                int value=(arr[i]/num)%10;
                result[--count[value]]=arr[i];
            }
            System.arraycopy(result,0,arr,0,arr.length);
           Arrays.fill(count,0);
       }



        return result;
       
       
       
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

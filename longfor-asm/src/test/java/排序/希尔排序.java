package 排序;

public class 希尔排序 {

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 9, 6, 5, 8, 1,10,15,12,14,13};

        for (int i = 4; i >0;i/=2){
            shell(arr, i);
        }

        for (int i : arr) {
            System.out.println(i);
        }
       int a= 9 ;
        a|=2;
    }

    public static void shell(int[] arr,int gap){
        for(int k=gap;k<arr.length;k++){
            for(int j=k;j>gap-1;j-=gap){
                if(arr[j]<arr[j-gap]){
                    swap2( arr, j, j-gap);
                }
            }
        }
    }


    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }


    public static void swap2(int[] arr,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}

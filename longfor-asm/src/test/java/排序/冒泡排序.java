package 排序;

public class 冒泡排序 {

    public static void main(String[] args) {
        int[] arr={2,3,4,7,9,6,5,8,1};

        for(int j=arr.length;j>0;j--){
            findMax(arr,j);
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }


    public static void findMax(int[] arr,int range){
        for(int k=0;k<range-1;k++){
            if(arr[k]>arr[k+1]){
                swap(arr,k,k+1);
            }
        }

    }


    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}

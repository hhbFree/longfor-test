package 排序;

public class 归并排序 {

    public static void main(String[] args){
        int[] arr={1,4,5,6,3,9,10};

        sort(arr);



    }


    public static void sort(int[] arr){
        int mid=arr.length/2;
        int start =0;
        int sed=mid+1;
        int[] tmp=new int[arr.length];
        int k=0;
        while(start<=mid&&sed<arr.length){
            tmp[k++]=arr[start]<arr[sed]?arr[start++]:arr[sed++];
        }
        while(start<=mid){
            tmp[k++]=arr[start++];
        }
        while(sed<arr.length){
            tmp[k++]=arr[sed++];
        }

        print(tmp);
    }


    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }


    public static void print(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }


}

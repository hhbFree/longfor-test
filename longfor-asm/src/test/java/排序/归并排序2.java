package 排序;

public class 归并排序2 {

    public static void main(String[] args){
        int[] arr={1,4,5,6,3,9,10};
        sort(arr,0,arr.length-1);

        print(arr);
    }


    public static void sort(int[] arr,int left,int right){

        if(right==left)return;

        //获取中间值
        int mid=(left+right)/2;

        sort(arr,left,mid);
        sort(arr,mid+1,right);

        merge(arr,left,mid+1,right);
    }

    public static void merge(int[] arr,int leftPoint,int rightPoint,int rightBound){
        int mid=rightPoint-1;
        int start =leftPoint;
        int sed=rightPoint;
        int[] tmp=new int[rightBound-leftPoint+1];
        int k=0;
        while(start<=mid&&sed<=rightBound){
            tmp[k++]=arr[start]<arr[sed]?arr[start++]:arr[sed++];
        }
        while(start<=mid){
            tmp[k++]=arr[start++];
        }
        while(sed<=rightBound){
            tmp[k++]=arr[sed++];
        }

        for (int i = 0; i < tmp.length; i++) {
            arr[leftPoint+i]=tmp[i];
        }

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

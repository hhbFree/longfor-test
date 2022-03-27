package 排序;

public class 插入算法 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 9, 6, 5, 8, 1};

        for (int i = 0; i < arr.length;i++){
            insert(arr, i);
        }

        for (int i : arr) {
            System.out.println(i);
        }

    }

        public static void insert(int[] arr,int range){
            for(int k=range;0<k;k--){
                if(arr[k]<arr[k-1]){
                    swap( arr, k, k-1);
                }else{
                    break;
                }
            }
        }


        public static void swap(int[] arr,int i,int j){
            int tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
}

package 排序;

public class 顺序排序 {

    public static void main(String[] args) {

        int[] arr={2,3,4,7,9,6,5,8,1};


        for(int i=0;i<arr.length;i++){
            int min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            int tmp=arr[i];
            arr[i]=arr[min];
            arr[min]=tmp;

        }

        for(int k=0;k<arr.length;k++){
            System.out.println(arr[k]);
        }

    }

}

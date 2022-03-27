package 排序;

public class 计数排序2 {
    /**
     * 范围小。频次高的排序
     * @param args
     */
    public static void main(String[] args) {
        int[] arr={0,1,0};
        int[] sort = sort(arr);
        print(sort);
    }



    public static int[] sort(int[] arr){
        int[] count=new int[arr.length];

        int[] res=new int[arr.length];

        //计算频次
        for(int i=0;i<arr.length;i++){
            count[arr[i]]=count[arr[i]]+1;
        }
        print(count);//[2,1]
        System.out.println();
        //稳定的计算
        for(int i=1;i<arr.length;i++){
            count[i]=count[i]+count[i-1];
        }
        print(count);//[2,3]
        for(int i=arr.length-1;i>=0;i--){
            res[--count[arr[i]]]=arr[i];
        }

        return res;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}

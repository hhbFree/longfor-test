package 排序;

public class 记数排序 {
    /**
     * 范围小。频次高的排序
     * @param args
     */
    public static void main(String[] args) {
        int[] arr={4,3,4,7,8,6,5,8,1};
        int[] sort = sort(arr);
        print(sort);
    }



    public static int[] sort(int[] arr){
        int[] re=new int[arr.length];

        int[] res=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            re[arr[i]]=re[arr[i]]+1;
        }
        int j=0;
        for(int i=0;i<arr.length;i++){
            while(0<re[i]--){
                res[j++]=i;
            }
        }
        return res;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

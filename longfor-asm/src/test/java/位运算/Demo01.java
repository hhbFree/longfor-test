package 位运算;


public class Demo01 {

    public int binaryGap(int N) {
        int[] arr=new int[32];
        int t=0;

        for (int i = 0; i < arr.length; i++) {
            if((N>>i&1)==1){
                arr[t++]=i;
            }
        }

        int ans=0;

        for (int i = 1; i < arr.length; i++) {
            ans=Math.max(ans,arr[i]-arr[i-1]);
        }
        return ans;
    }


}

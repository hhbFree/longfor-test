package 排序;

public class 快速排序 {

    public static void main(String[] args) {
        int[] arr = { 2,1,5,10,5};
        sort(arr, 0, arr.length-1);
        print(arr);
    }


    public static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] re =new int[2];
        int l=1;
        int r=1;
        String s="1";
        if( s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        re[0]=l;
        re[1]=r;
        int mid = merge(arr, left, right);

        sort(arr, left, mid - 1);

        sort(arr, mid + 1, right);
    }

    /**
     * 返回中轴线
     */
    public static int merge(int[] arr, int leftPoint, int rightPoint) {
        int povit = arr[rightPoint];
        int left = leftPoint;
        int right = rightPoint-1;

        while (left<right) {

            while (left <= right && arr[left] <= povit) {
                left++;
            }

            while (left <=right && arr[right] > povit) {
                right--;
            }

            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr,left,rightPoint);
        return left;

    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}

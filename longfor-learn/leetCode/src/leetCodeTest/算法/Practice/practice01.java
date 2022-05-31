package leetCodeTest.算法.Practice;
/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class practice01 {

    public static void main(String[] args) {
        int[] nums={2, 7, 11, 15};

       int[] index= getIndexAdd(nums,9);

    }

    private static int[] getIndexAdd(int[] nums, int target) {
        int[] re=new int[2];
        for (int j = 0; j <nums.length-1 ; j++) {
            for (int i = j+1; i <nums.length ; i++) {
                if(nums[j]+nums[i]==target){
                    re[0]=j;
                    re[1]=i;
                    return  re;
                }
            }
        }
        return re;
    }
}

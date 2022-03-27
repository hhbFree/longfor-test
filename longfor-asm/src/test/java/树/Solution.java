package 树;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        List<String> list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedBlockingDeque<>();
        //边界 >1
        if(root==null){
            return false;
        }
        TreeNode p=root;
        //遍历入列
        while(p!=null){
            list.add(p.val+"");
            if(p.left!=null){
                queue.add(p.left);
            }
            if(p.left!=null){
                queue.add(p.left);
            }
            p=queue.poll();
        }
        list.subList(list.size() / 2, list.size() - 1);
        
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i));
        }
        //分割数组  （length/2，length-1）

        //判读左右两列遍历判断

        //边界 分割数组>=2 %2=0 

        return false;
    }
}
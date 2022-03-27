package 链表.demo02;

import 链表.ListNode;

class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode re=new ListNode(0);
        ListNode deal=re;
        while(l1!=null){
            int sum=l1.val+l2.val;
//            if(deal!=null){
//                sum=+deal.val;
//            }else{
//                deal=new ListNode(0);
//            }
            if(deal.next==null){
                deal.next=new ListNode(0);
            }
            sum+=deal.val;
            if(sum>=10){
                int y=sum%10;
                deal.val=y;
                deal.next=new ListNode(1);
            }else{
                deal.val=sum;
            }

            l1=l1.next;
            l2=l2.next;
            deal=deal.next;
        }
        return re;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(2);

        ListNode head2=new ListNode(1);
        head2.next=new ListNode(8);
        head2.next.next=new ListNode(1);


        ListNode listNode = addTwoNumbers(head,head2);

        System.out.println(listNode);
    }
}
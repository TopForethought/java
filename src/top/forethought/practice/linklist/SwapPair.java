package top.forethought.practice.linklist;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author  wangwei
 * @date     2019/3/12 12:44
 * @classDescription  链表对交换
 * 比如输入;1->2->3->4->5
 *     输出;2->1->4->3->5
 *     // 输入1->2->3->4
 *     输出:2->1->4->3
 *
 */

public class SwapPair {

    public static void main(String[] args) {
        int []datas={1,2};
        ListNode head=new ListNode(datas[0]);
        ListNode curr=head;
        for(int i=1;i<datas.length;i++){
            curr.next=new ListNode(datas[i]);
            curr=curr.next;
        }
       head=  new SwapPair().swap(head);
        System.out.println(head);

    }
    public  ListNode swap(ListNode head){
     if(head==null||head.next==null){
         return head;
     }


     ListNode pre=new ListNode(0);// 空白节点,充当 前继节点
         ListNode result=head.next;
         pre.next=head;

     while (pre.next!=null && pre.next.next!=null){
         ListNode curr=pre.next;
         ListNode next=curr.next;
         ListNode nextnext=next.next;
         pre.next=next;
         next.next=curr;
         curr.next=nextnext;
         pre=curr;

     }


     return result;
    }
}

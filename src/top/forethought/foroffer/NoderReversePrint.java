package top.forethought.foroffer;

import java.util.ArrayList;
import java.util.List;

/**
 *  倒着打印链表, 输入 [1,2,3]
 *   输出[3,2,1]
 */
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
public class NoderReversePrint {
    // 递归
     List<Integer> res=new ArrayList<>();
    public int[] printListReversingly(ListNode head) {
        reversePut(head,res);
        int [] resArray=new int[res.size()];
        int i=0;
        for(Integer val:res){
            resArray[i++]=val;
        }

         return resArray;
    }

    private void reversePut(ListNode head, List<Integer> res) {
             if(head.next!=null){
                 reversePut(head.next,res);
             }
             res.add(head.val);

    }
}

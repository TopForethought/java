package top.forethought.practice.linklist;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author  wangwei
 * @date     2019/3/12 15:08
 * @classDescription  判断链表是否存在环
 *
 *        1. 判断是否有环
 *        2. 确定第一个进入环的节点(
 *           思路;先判断是否有环   快慢指针第一次相遇
 *           // 设 进入环之前前进节点数  a
 *           //   慢指针在环中前进   节点数为c
 *
 *           //环周长 s
 *           // 慢指针  总前进数:a+c
 *           // k快指针   a+c+k*s(第一次相遇 k 取1)
 *           // 等式  a+c+s=2*(a+c)
 *           //       s=a+c ;  表示 此时慢指针恰好 走了一个环的距离
 *           // a=s-c       也就是 慢指针再走 a的节点数就能到达入口
 *           // 此时可以用一个满指针从头开始追,与之前的慢指针相遇时,说明这就是入口
 *
 */
public class ListCycle {

    // 方式1;最简单的方法,使用set去重
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> set=new HashSet<>();
            int size=set.size();
            while(head!=null){
                set.add(head);
                if(size==set.size()){
                    return head;
                }
                size=set.size();
                head=head.next;
            }
            return null;
        }
        // 方式2 龟兔赛跑
    // 快慢指针
        public ListNode detectCycle2(ListNode head) {
            ListNode slow=head;
            ListNode fast=head;
            while (fast!=null && fast.next!=null){
                if(fast.equals(slow)){
                    return slow;
                }
                slow=slow.next;

                fast=fast.next.next;
            }

            return null;
        }

}

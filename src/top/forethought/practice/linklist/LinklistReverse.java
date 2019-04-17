package top.forethought.practice.linklist;

/**
 * @author  wangwei
 * @date     2019/3/12 12:16
 * @classDescription  链表反转
 *
 */
 class ListNode {
      int val;
     ListNode next;
     ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
public class LinklistReverse {
    public ListNode reverseList(ListNode head) {
        ListNode pre=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode next=curr.next;// 暂存后继节点
            curr.next=pre;// 当前节点的next指向前继节点
            pre=curr;// 前继节点指针后移,指向当前节点
            curr=next;// 当前节点后移
        }
        return pre;

    }
    // 递归反转
    public ListNode recurisonReverseList(ListNode pre,ListNode curr){
      if(curr==null){
          return pre;
      }
        ListNode next=curr.next;
      curr.next=pre;
      pre=curr;
      curr=next;
      return recurisonReverseList(pre,curr);
    }

    public static void main(String[] args) {
        int []datas={1,2,3,4,5};
        ListNode head=new ListNode(datas[0]);
        ListNode curr=head;
        for(int i=1;i<datas.length;i++){
            curr.next=new ListNode(datas[i]);
            curr=curr.next;
        }
        //System.out.println(head);

       // head=new LinklistReverse().reverseList(head);
       // System.out.println("反转后");
       // System.out.println(head);
       head= new LinklistReverse().recurisonReverseList(null,head);
        System.out.println(head);
    }
}

package top.forethought.linklist;

import java.util.Scanner;

/**
 * @author wangwei
 * @date 2019/3/28 21:30
 * @classDescription 最近最少使用, 放在链表末尾
 * 使用双向链表:
 * 1,pop (查找元素,将目标元素放到链表头部,然后返回数据)
 * 2,push 添加元素,检查size,size如果超过最大,移除最后一个节点,将新节点插入链表头部
 */
public class LRUCache<K,V> {
    private int MAX_SIZE;// cache 最大容量
    private int size;
    private DoNode sentryNode;// 虚拟节点,pre 指向头节点,next 指向尾节点

    public LRUCache(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        sentryNode = new DoNode();
        sentryNode.next = sentryNode;
        sentryNode.pre = sentryNode;

    }

    // 查找数据,并将节点返回
    public DoNode<K,V> pop(K key) {
        DoNode target = search(key);
        if (null == target) {
            return null;
        }
        //将target 调整到头部
        moveExistsNodeToHead(target);
        return target;
    }

    public void push(K key,V data) {

        DoNode lookResult = search(key);
        if (lookResult != null) {
            moveExistsNodeToHead(lookResult);
            return;
        }


        if (size >= MAX_SIZE) {
            removeLast();
        }
        // 插入新节点
        insertBeforeHead(key,data);
    }

    private DoNode search(K key) {
        DoNode result = sentryNode.next;
        // 不要绕圈查找,如果到了哨兵还没查找到,说明缓存不存在
        while (result != null && (!result.equals(sentryNode))) {
            if (key.equals(result.key)) {
                break;
            }
            result = result.next;
        }
        return sentryNode.equals(result) ? null : result;
    }

    //将node 调整缓存中存在的节点到头部
    private void moveExistsNodeToHead(DoNode target) {
        //将target 调整到头部
        target.pre.next = target.next;
        target.next = target.pre;

        target.pre = sentryNode;
        sentryNode.next.pre = target;
        target.next = sentryNode.next;
        sentryNode.next = target;
    }

    //删除尾节点
    private void removeLast() {
        if (size <= 0) {
            throw new RuntimeException(" 不能继续删除尾节点");
        }
        sentryNode.pre = sentryNode.pre.pre;
        sentryNode.pre.next = sentryNode;
        size--;
    }

    // 头插入节点
    private void insertBeforeHead(K key,V data) {
        DoNode node = new DoNode(key,data);
        node.pre = sentryNode;
        node.next = sentryNode.next;
        node.next.pre = node;
        sentryNode.next = node;

        size++;
    }

    public static void main(String[] args) {
        LRUCache<Integer,Integer> cache = new LRUCache<>(3);
        Scanner in = new Scanner(System.in);
        Integer[] input = {1, 2, 3, 2, 3};
        for (Integer data : input) {
            Integer key=data-1;
            cache.push(key,data);
        }


    }


    class DoNode<K,V> {
        K key;
        V data;
        DoNode pre;
        DoNode next;

        public DoNode(K key,V data) {
            this.key=key;
            this.data = data;
        }

        public DoNode() {
        }
    }
}




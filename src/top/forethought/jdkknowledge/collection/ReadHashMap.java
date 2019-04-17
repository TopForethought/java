package top.forethought.jdkknowledge.collection;

import java.util.HashMap;

/**
 * @author  wangwei
 * @date     2019/3/18 21:10
 * @classDescription
 *  HashMap  是数组+链表的形式
 *  通过 hash % 数组大小得到在数组中的位置,然后将新节点放在该位置的链表头部(头插法,可能涉及到局部性原理)
 *
 *  get  是先根据 hash(key)获取到数组中的位置,在根据key比较,找到对应的元素节点
 *  put:  当链表的长度超过8时,转为红黑树,提高查询效率(o(lgn))     长度不超过8时,平均查找长度是(1+8)/2=4.5  ,如果是使用红
 *        黑树则会提升至lgn  ,lg8=3
 *        红黑树:是自平衡二叉树,红黑树保证了所有到叶子的路径上黑色节点一样多,也就保证了不会出现路径是最短路径两倍的情况
 *                 在执行插入操作时,通过旋转维持红黑树的性质
 *        但是为什么不使用AVL,平衡二叉树呢??
 *
 *  hash  函数时将高位的影响扩散到低位
 */

public class ReadHashMap {
    public static void main(String[] args) {


        System.out.println(1<<2);
        System.out.println(1<<2);
    }
}

package top.forethought.practice;

import top.forethought.trees.BTreeNode;

/**
 * @author  wangwei
 * @date     2019/3/19 11:05
 * @classDescription
 * 给定二叉树,判断是否是排序二叉树
 * 思路:1,中序遍历升序
 *      2,递归判断三个节点的带线大小关系
 *      如果满足: bool lSuccess=rec(left)
 *                 if(lSuccess){
 *                     return rec(r)
 *                 }
 *
 */
public class ValidateBST {


    boolean validateBST(BTreeNode<Integer> node){
        if(null==node){
            return true;
        }
        BTreeNode<Integer> left=node.getLChild();
        BTreeNode<Integer> right=node.getRChild();
        if(left!=null && right!=null){
            if(left.getData()>=node.getData() ||node.getData()>=right.getData()||left.getData()>=right.getData()){
               return false;
            }
        }
        boolean leftIsBST=validateBST(left);
        return leftIsBST?leftIsBST:validateBST(right);

    }

    public static void main(String[] args) {

    }
}

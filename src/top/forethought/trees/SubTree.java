package top.forethought.trees;

import java.util.ArrayDeque;
import java.util.Deque;

// 暂时此方法未通过

/**
 *
 */
public class SubTree {



    public boolean HasSubtree(TreeNode root1,TreeNode root2) {


         if(root2==null){
             return false;
         }
        if(root1==null){
            return false;
        }
         Deque<TreeNode> deque=new ArrayDeque<>();
         deque.add(root1);

         for(;!deque.isEmpty();){

             boolean success=doSearch(deque.peek(),root2);
             if(success){
                 return  true;
             }
            TreeNode out=   deque.remove();
             if(out.left!=null){
                 deque.add(out.left);
             }
             if(out.right!=null){
                 deque.add(out.right);
             }
         }
         return false;
    }

    boolean doSearch(TreeNode root1,TreeNode root2){

        if(root1==null && root2==null){
            return true;
        }

      //
        if   (  root1!=null && root2!=null && root1.val==root2.val){
            if(root2.left==null && root2.right==null){
                return true;
            }
            return doSearch(root1.left,root2.left)&& doSearch(root1.right,root2.right);
        }
        return false;

    }

}
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
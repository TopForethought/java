package top.forethought.foroffer.acwing.dfs_week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  给定一个数组(元素不重复）
 *  输出：所有的子集
 */

public class Leetcode78Subsets {

      List<List<Object>> finalRes;
      Stack<Object> path;
     public  List<List<Object>> solution(Object [] arrs){
        finalRes=new ArrayList<>();
        path=new Stack<>();
        dfs(arrs,0);
        return finalRes;
    }
// 以start 开头的子集
    private void dfs(Object[] arrs, int start) {
      finalRes.add(new ArrayList<>(path));
         for(int i=start;i<arrs.length;i++){
             path.push(arrs[i]);
             dfs(arrs,i+1);
             path.pop();

      }



    }

    public static void main(String[] args) {
        System.out.println(new Leetcode78Subsets().solution(new Object[]{1,2,3}));
    }
}

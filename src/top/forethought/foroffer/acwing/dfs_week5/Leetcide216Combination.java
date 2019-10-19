package top.forethought.foroffer.acwing.dfs_week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入:k,n 从1-9之间不重复选择k 个数,使得其和为n
 * 输入: 满足上述条件的组合数
 * 枚举起点 i, 然后递归   k-1,n-arr[i]
 */
public class Leetcide216Combination {

    List<List<Integer>> finalRes;
    Stack<Integer> path;

    public List<List<Integer>> solution(int k, int n) {
        finalRes = new ArrayList<>();
        path = new Stack<>();
        dfs(k, n, 1);
        return finalRes;
    }

    private void dfs(int k, int n, int start) {
       if(k==0 && n==0){
           finalRes.add(new ArrayList<>(path));// 一次成功的组合
           return;
       }
       if(n<0||k<0){ //枚举失败
           return ;
       }
       for(int i=start;i<=9;i++){
           path.push(i);// 压入节点
           dfs(k-1,n-i,i+1);
           path.pop();// 恢复现场
       }
    }

    public static void main(String[] args) {
    System.out.println(    new Leetcide216Combination().solution(3,7));
    }
}

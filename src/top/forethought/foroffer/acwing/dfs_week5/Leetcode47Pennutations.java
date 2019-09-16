package top.forethought.foroffer.acwing.dfs_week5;
// 有重复数字的 全排列
// 比如输入  1，2，2
// 输出 1 2 2 ，2 1 2 ，2 2 1
// 关键点：如何处理重复元素的排列问题
// 解决：保证 相同元素的相对位置不发生改变
// 如何让相同元素集中放置？
//  排序

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Leetcode47Pennutations {
   List<List<Integer>> finalRes;
   Stack<Integer> path;
   boolean []used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        finalRes=new ArrayList<>();

        path=new Stack<>();
        used=new boolean[nums.length];
        // 排序：让相同数字集中
        Arrays.sort(nums);
        dfs(nums,0,0);
        return finalRes;
    }

    /**
     *
     * @param nums
     * @param level 当前递归层数
     * @param start 当前递归元素 可以放置的下标的起点
     */
    private void dfs(int[] nums, int level, int start) {

        // 此全排列完成
        if(path.size()==nums.length){
            finalRes.add(new ArrayList<>((Stack<Integer>)path.clone()));
            return ;
        }

        // k 则是相同元素的个数
        for(int i=start;i<nums.length;i++){
            if(used[i]){
                continue;
            }
            // 如果当前元素与上一个元素相同，并且上一个位置已经被使用，那则是下一层
            // 如果上一个位置未被使用，那么当前元素不能占用上一个位置 ，否则就出现
            // 相同元素的相对位置发生改变
            if( i>0 && nums[i]==nums[i-1] && !used[i-1]){
                continue;
            }

                used[i]=true;
                path.push(nums[i]);
                dfs(nums,level+1,0);
                used[i]=false;
                path.pop();

        }
    }

    public static void main(String[] args) {
    System.out.println( new Leetcode47Pennutations().permuteUnique(new int[]{1,2,2}));
    }
}

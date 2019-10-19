package top.forethought.foroffer.acwing.dp_week8;

/**
 * 最长上升子序列
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Leetcode300 {
    public int lengthOfLIS(int[] nums) {
        if(nums==null){
            return 0;
        }

        // dp[i] 表示以 第i 个元素结尾的最长上升子序列长度
        //找到nums[i] 左边第一个比自己小的数 k(在数组中是 第k 个数)
       // 以nums[i] 结尾的上升子序列最大长度
        int maxLength=0;
        int []dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
           dp[i]=1;
            for(int j=0;j<i;j++){
               if(nums[j]<nums[i]){
                   // 序列包含nums[j] 还是不包含nums[j] 所取得最大长度
                   if(dp[j]+1>dp[i]){
                       dp[i]=dp[j]+1;
                   }
               }
           }
            maxLength=maxLength<dp[i]?dp[i]:maxLength;
       }


        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode300().lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }
}

package top.forethought.foroffer.acwing.stack_queue_window_week6;

/**
 *  输入：升序数组
 *  输出： 求出和为指定值得两个元素的下标 （下标为 非 0 开始的）
 */
public class Leetcode167TwoSum {

    // 方式1：遍历 复杂度 o(n2）
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[2];
    }

    /**
     *  使用双指针：借助于单调性  实现 o（n） 的效果
     *
     *   letf=0,right=length-1
     *    num[left]+num[right]>target,left-- ;// 注意升序的单调性,先固定left,right 减少，判断 是否有满足条件（num[left]+num[right]<=target）的right,
     *    如果没有，说明 [left,right0] 区间找不到满足条件的两个元素
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int right,left;
        right=nums.length-1;
        for(left=0;left<nums.length;left++){
            while(right-1>left && nums[left]+nums[right]>target){
                right--;
            }

            if(nums[left]+nums[right]==target){
                return new int[]{left+1,right+1};
            }
        }
        return new int[]{-1,-1};
    }

}

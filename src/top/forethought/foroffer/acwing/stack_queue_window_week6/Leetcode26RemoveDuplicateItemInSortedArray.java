package top.forethought.foroffer.acwing.stack_queue_window_week6;

/**
 *  删除有序数组中的重复元素// 原地删除，额外使用o(1) 的空间实现
 *  并且返回最终的数组长度
 */
public class Leetcode26RemoveDuplicateItemInSortedArray {
    public int removeDuplicates(int[] nums) {
  //保留的元素从前往后放置
        // 最终的效果是最后会连续几个亏空
        // 核心：如何统计重复元素
        int repeatCount=0;
        int  putIndex=0;
        for(int i=0;i<nums.length;i++){

            int k=0;
            // 寻找重复元素的个数，从当前元素的后一个元素开始
            while (i+k+1<nums.length && nums[i+k+1]==nums[i]){
                k++;
            }
            repeatCount+=k;

            // i+k+1 是与nums[i] 不同的元素
            nums[putIndex]=nums[i+k];// 保留相同元素中的一个
            // 更新下一次i 开始搜索的位置
            i=i+k+1-1;
            putIndex++;
        }

        return nums.length-repeatCount;
    }
    // 代码简化
    public int removeDuplicates2(int[] nums) {
        //保留的元素从前往后放置
        // 最终的效果是最后会连续几个亏空

        int  putIndex=1;// 慢指针
        for(int i=1;i<nums.length;i++){//i 是快指针

            if(nums[i]!=nums[i-1]){
                nums[putIndex]=nums[i];
                putIndex++;

            }

        }

        return putIndex;
    }
}

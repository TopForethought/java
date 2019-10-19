package top.forethought.foroffer.acwing.stack_queue_window_week6;

/**
 *  输入：两个有序数组
 *  输出：将数组2合并到第一个数组中
 *  双指针：时间复杂度o(m+n）
 */
public class Leetcode88MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int putIndex=m+n-1;
        int max2=n-1;
        int max1=m-1;
        while(max1>=0 && max2>=0){
            if(nums1[max1]>=nums2[max2]){
                nums1[putIndex--]=nums1[max1--];
            }else{
                nums1[putIndex--]=nums2[max2--];
            }
        }
        // 有其中给一个数组放置提取完成
        while(max1>=0){
            nums1[putIndex--]=nums1[max1--];
        }
        while(max2>=0){
            nums1[putIndex--]=nums2[max2--];
        }

    }


}

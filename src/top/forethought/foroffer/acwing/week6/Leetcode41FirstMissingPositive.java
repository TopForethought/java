package top.forethought.foroffer.acwing.week6;

/**
 * class: 第一个缺失的正数（ 实现有问题）
 * author: wangwei
 * time : 2019/10/11
 * <p>
 * 输入： 整数数组，可能包含负数，无序
 * 输出：第一个缺失的正数    比如： 输入：1，3，-1 那么输出就是 2
 * [7,8,9,11,12]
 * 要求：时间复杂度 o(n)。空间 0(1)  使用布隆过滤器？？
 * <p>
 * 第一次遍历 将 >=1 和<数组长度的数字 放到 对应下标位置
 * 将1-nums.length 的数字 k 放置在 数组的 k-1 位置上
 * // 加上标记 如果正数 i 出现 则将 i-1 位置标记为
 */
public class Leetcode41FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        // 去除负数 和0 的干扰
        //如果没有1 那么就一定缺失1
        // 如果有1 ，那么就将负数和0替换为1
        boolean findNumberOne = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                findNumberOne = true;
                break;
            }
        }
        if (!findNumberOne) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] <= 0 ? 1 : nums[i]; // <=0 的数使用1 替换
            nums[i] = nums[i] > nums.length ? 1 : nums[i];                 // 大于nums.length 的数使用 1 替换

        }
        // 合适的数字 放在应该放置的位置  注意： 交换不能无脑的交换 如果当前位置放置的不是k,则向后搜索，搜到k 与该位置数字交换

        // 上面数字范围 都是 【1，nums.length】
        // 数组中存在 数字1   , 数字 k 放在 位置 k-1
        // 索引为 i
        // 当前位置数字是nums[i]
        //当前搜索数字为 k   ,
        // 如果数字 k==nums[i], 继续搜索 k+1
        // 如果数字 k<nums[i]// 说明 数字 k 最多可能在 nums 中的i 位置之后出现， 从i+1 位置开始搜索 是否存在数字k
        // 如果数字k>nums[i]  //说明nums[i] 是重复出现的数字（之前就已经处理过）


        for (int i = 0; i < nums.length; i++) {
            int k = i + 1;    // i 相当于是k-1
            // 当前位置应该放置 数字k ，但是该位置不是 数字k,则需要向后搜索数字k ,如果搜到，交换到该位置，未搜到，返回该数字(表示缺失）
            int temp = k - 1;
            while (temp < nums.length && nums[temp] != k) {
                temp++;
            }
            if (temp >= nums.length) {
                return k;
            }
            swap(nums, temp, k - 1);
        }
        // 寻找值 和下标不对应的数字

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //1.  数组先 判断是否存在1
    //2.  如果存在1 ，则将不再合法范围的数字替换为1
    //3.  使用正负号标记数字 k 是否出现过
    public int firstMissingPositive2(int[] nums) {
        boolean findNumberOne = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                findNumberOne = true;
                break;
            }
        }
        if (!findNumberOne) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] <= 0 ? 1 : nums[i]; // <=0 的数使用1 替换
            nums[i] = nums[i] > nums.length ? 1 : nums[i];                 // 大于nums.length 的数使用 1 替换
        }
        // 开始标记
        for (int i = 0; i < nums.length; i++) {
            int k =Math.abs( nums[i]);
            int origin = nums[Math.abs(k)];
            nums[Math.abs(k)] = -origin;
        }
        // 开始判断，如果该位置为正数，表示该位置代表的数字缺失
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }


        return nums.length + 1;
    }

    public static void main(String[] args) {
//          System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{1,2,3}));
//        System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{1,2,0}));
//          System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{2,1}));
//      System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{3,4,-1,1}));
//          System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{-1,4,2,1,9,10}));
        //  System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{1,3,-1,1,2,4,5,1}));
        System.out.println(new Leetcode41FirstMissingPositive().firstMissingPositive(new int[]{10, 4, 16, 54, 17, -7, 21, 15, 25, 31, 61, 1, 6, 12, 21, 46, 16, 56, 54, 12, 23, 20, 38, 63, 2, 27, 35, 11, 13, 47, 13, 11, 61, 39, 0, 14, 42, 8, 16, 54, 50, 12, -10, 43, 11, -1, 24, 38, -10, 13, 60, 0, 44, 11, 50, 33, 48, 20, 31, -4, 2, 54, -6, 51, 6}));
    }
}

package top.forethought.foroffer.acwing.dfs_week5;

import java.util.Arrays;

/**
 * 火柴拼正方形，火柴长度可能不一样，不能折断
 * 需要判断能否完全使用输入的火柴拼接成一个正方形
 * 输入：正整数数组
 * 输出：判断输入的数据能否凑成一个正方形的四条边
 * 注意：每个数字不能拆分，但是可以拼接
 */

public class Leetcode473MatchsticksToSquare {
    // 思路：
    // 1. 所有火柴长度之和被4整除
    // 2. 枚举每条边的构成
    //  对于每条边，从长度较大的火柴开始枚举
    //3. 假设每条边内部构成的火柴长度都是 从 大到小排列的
    // 剪枝
    // 1. 如果当前木棒拼接失败，则跳过接下来的与当前木棒相同的木棒，
    // 2. 如果当前木棒拼接失败，且是当前边第一个木棒，则结束当前分支
    // 3. 如果当前木棒拼接失败，且是当前边最后一个木棒，则结束当前分支
    boolean[] used;

    public boolean makesquare(int[] nums) {
        // 1. 必要条件，所有火柴长度之和能被4整除
        int sum = 0;
        for (int l : nums) {
            sum += l;
        }
        if (sum == 0 || sum % 4 != 0) {
            return false;
        }
        used = new boolean[nums.length];

        // 2. 排序，降序排列
        Arrays.sort(nums);// 默认是升序排列

        return dfs(nums, 0, 0, sum / 4);

    }

    /**
     * @param nums
     * @param lineIndex  当前拼接的是第几条边
     * @param currLength 当前边已拼接长度
     * @param length     每边应该的长度
     * @return
     */
    private boolean dfs(int[] nums, int lineIndex, int currLength, int length) {
        // 1. 当前边拼接完成，开始拼接下一条边
        if (currLength == length) {
            currLength = 0;
            lineIndex++;

        }
        // 2. 已经完成四条边的拼接
        if (lineIndex == 4) {
            return true;
        }

        // 3. 当前边未拼接完，继续枚举下一根火柴
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!used[i] && nums[i] + currLength <= length) {
                used[i] = true;
                if (dfs(nums, lineIndex, currLength + nums[i], length)) {
                    return true;
                }
                used[i] = false;
                // 剪枝：
                // 当前木棒添加失败，并且是该边第一个木棒，直接删除当前分支
                if (currLength == 0) {
                    return false;
                }
                // 当前木棒拼接失败，并且是该边最后一个木棒，直接删除当前分支
                if(i==0){
                    return false;
                }
                // 当前火柴拼接未成功，跳过接下来长度和当前火柴长度一样的

                while (i - 1 >= 0 && nums[i - 1] == nums[i]) {
                    i--;
                }
            }
        }

        return false;
    }

}

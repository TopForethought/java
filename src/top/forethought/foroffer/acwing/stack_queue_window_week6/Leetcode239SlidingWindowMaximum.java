package top.forethought.foroffer.acwing.stack_queue_window_week6;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * class:  滑动窗口最大值
 * author: wangwei
 * time : 2019/10/13
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 暴力的做法：维护一个 容量为k 的 大根堆，时间复杂度是 nlgk
 * 线性复杂度： 使用 单调队列 ，单调递减的队列
 * 针对 nums[i], 如果nums[i] <que.getFast 表示 nums[i] 可能出现在后续之中的最大值
 * num[i]>que.getFast()  表示 nums[i] 是新的最大值
 */
public class Leetcode239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int resPos = 0;
        // 单调递减队列(注意队列中存放的是元素下标)
        // 队列头部涉及删除  :
        // 队列尾部涉及添加和删除
        // 新元素如果 大于队列头部,添加到队列头部
        // 新元素如果 小于队列尾部,添加到队列尾部
        // 否则删除队列尾部
        for (int i = 0; i < nums.length; i++) {
            // 如果 队列头部元素位置 小于 i-k+1 说明该元素不在 当前区间内,需要删除
            if (!deque.isEmpty() && deque.getFirst() < i - k + 1) {
                deque.removeFirst();
            }
            // 处理队尾元素,新元素较大,删除队尾较小元素 ,来保障队列单调递减
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // 如果当前 区间已经符合 长度为 k ,说明可以统计结果
            if (i >= k - 1) {
                res[resPos++] = nums[deque.getFirst()];
            }

        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode239SlidingWindowMaximum().maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3));
        //  1 3
        //    3
        //
    }
}

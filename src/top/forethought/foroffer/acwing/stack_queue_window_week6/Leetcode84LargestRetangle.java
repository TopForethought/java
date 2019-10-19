package top.forethought.foroffer.acwing.stack_queue_window_week6;

import java.util.Stack;

/**
 *  输入是直方图 的高（每个柱形宽都是1）
 *  输出是 直方图能够截取的最大矩形面积
 *
 *
 *  分析：
 *  1. 如何枚举所有能够构成的矩形呢？
 *    枚举 构成矩形的高即可
 *    每个柱形都有一个高
 *    接下来需要确定 该高 所在柱形 左右开两侧延伸宽，使得宽最大，
 *     此时取得一当前高 确定的最大矩形面积
 *    2. 如何获取以某柱形周围第一个比自己矮的柱形呢？ 这样就能确定最远的那个不比自己矮的矩形，这样就能由当前柱形的高来作为截取的柱形的高
 *      使用单调栈： 从左往右使用单调递增的栈  ,通过弹栈找到左侧第一个比自己矮的柱形
 *
 *
 */

public class Leetcode84LargestRetangle {
    public int largestRectangleArea(int[] heights) {
       int []leftMin=new int[heights.length];   // leftMin[i] 表示柱形i 的左侧第一个比自己矮的柱形的位置
       int  [] rightMin=new int[heights.length];
       Stack<Integer> stack=new Stack<>();
       // 从左往右
       for(int i=0;i<heights.length;i++){
           // 弹出比当前柱形高度高的柱形
           while (!stack.isEmpty() && heights[i]<=heights[stack.peek()]){
               stack.pop();
           }
           // 压入当前元素
           leftMin[i]=stack.isEmpty()? -1:stack.peek();
           stack.push(i);
       }
       stack.clear();
        // 从右往左
        for(int i=heights.length-1;i>=0;i--){
            // 弹出比当前柱形高度高的柱形
            while (!stack.isEmpty() && heights[i]<=heights[stack.peek()]){
                stack.pop();
            }
            // 压入当前元素
            rightMin[i]=stack.isEmpty()? 0:stack.peek();
            stack.push(heights.length);
        }
        // 计算面积
        int res=0;
        for(int i=0;i<heights.length;i++){
            int temp=(rightMin[i]-leftMin[i])*heights[i];
            res=res<temp?temp:res;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode84LargestRetangle().largestRectangleArea(new int[]{1}));
    }
}

package top.forethought.foroffer.acwing.stack_queue_window_week6;


import java.util.HashSet;

/**
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * 单调队列（滑动窗口）
 */

public class Leetcode76MinimumWindowSubstring {
    //思路:
    // 双指针
    // slow，fast
    // fast 移动，直到 slow,fast 区间出现所有 T 中的字符（包含次数） hash 或者数组记录 各字符出现次数
    // 移动slow ，字符出现次数减少 ，同时需要判断当前区间 是否和服条件，然后更新结果
    // 复杂度：o(2n)
    int[] appearCount;//记录i,j 区间各种字母出现的次数
    // 应该只记录 字符串 T 含有的字母
    public String minWindow(String s, String t) {

        appearCount = new int[58];
        // 1.统计 t 中不同字母数
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            hashSet.add(c);
            appearCount[c - 'A']++;
        }
        String res = null;
        int tCount = hashSet.size();
        int sameCount = 0;//记录当前区间已经出现了 t f符合条件的字符数,这个变量不考虑字符次数
        //2 .开始双指针搜索
        for (int slow = 0, fast = slow; slow < s.length(); slow++) {

           //移动窗口右边界
            while (sameCount < tCount && fast < s.length()) { //
                char c = s.charAt(fast++);
                appearCount[c - 'A']--; // -- 表示区间新增字符
                if (hashSet.contains(c) && appearCount[c - 'A'] == 0) {
                    sameCount++;// 符合T 的字符数+1(这里是区别了相同字符）
                }

            }
            // 处理 sameCount
            if (sameCount == tCount) {
                if (res == null || res.length() > fast - slow) {
                    res = s.substring(slow, fast);
                }
            }
            // 移动慢指针,窗口左边界右移之前，移除元素
            char c = s.charAt(slow);
            appearCount[c - 'A']++;
            if (hashSet.contains(c) && appearCount[c - 'A'] == 1) {
                sameCount--;
            }
        }
        return res==null?"":res;

    }

    public static void main(String[] args) {
        System.out.println(new Leetcode76MinimumWindowSubstring().minWindow("AbC","Ab"));
    }
}

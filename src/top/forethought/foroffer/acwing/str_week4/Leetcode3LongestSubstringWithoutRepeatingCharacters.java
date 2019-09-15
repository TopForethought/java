package top.forethought.foroffer.acwing.str_week4;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * author : wangwei
 * description: 请你找出其中不含有重复字符的 最长子串 的长度。
 * date:      2019/8/31
 */
public class Leetcode3LongestSubstringWithoutRepeatingCharacters {
 // 使用队列  ，如果当前符号在队列中存在
    // 移除第一次出现的当前元素及其之前元素，当前元素入队
    public int lengthOfLongestSubstring(String s) {
        BlockingDeque<Character> queue=new LinkedBlockingDeque<>();
        int maxLength=0;
        for(int i=0;i<s.length();i++){
            char curr=s.charAt(i);
            if(queue.contains(curr)){
                // 计算当前子串的长度
                maxLength=maxLength<queue.size()?queue.size():maxLength;
                // 移除上一次出现的字符及其之前的数据
                while (queue.poll()!=curr);
            }
            queue.add(curr);//队列尾部加入
            maxLength=maxLength<queue.size()?queue.size():maxLength;
        }

       return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode3LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("dvdfeqg"));
    }

}

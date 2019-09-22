package top.forethought.foroffer.acwing.week6;

import java.util.HashMap;
import java.util.HashSet;

/** 实现存在问题
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 */

public class Leetcode76MinimumWindowSubstring {
    //思路:对于 s 的[i,j] 区间,使用hash 记录此区间各种字符的个数
    //  双指针: i,j (j 往后走,直到i,j 区间包含 T 的每个字符
    //          此时 i 往后走,可以进一步 缩小[i,j] 区间
    //           直到[i,j]区间不完全包含 T 的每个字符,表示 此时已经达到了当前遍历区间的最小符合条件的区间
    //  i=j+1, j 往后走
     int [] appearCount;//记录i,j 区间各种字母出现的次数
    // 应该只记录 字符串 T 含有的字母
    public String minWindow(String s, String t) {

        appearCount=new int[26];
        // 1.统计 t 中不同字母数
        HashSet<Character> hashSet=new HashSet<>();
        for(int i=0;i<t.length();i++){
            char c=t.charAt(i);
            hashSet.add(c);
            appearCount[c-'A']++;
        }
        String res=null;
        int tCount=hashSet.size();
        //2. 开始枚举区间
        int count=0;//记录当前区间已经出现了 t 中的count 个不同字符
        // i 是快指针
        // j 是慢指针
       for(int i=0,j=0;i<s.length();i++){
           char c=s.charAt(i);
           if(hashSet.contains(c) && appearCount[c-'A']==1){
               count++;// 符合T 的字符数+1
           }
           //区间字符移除一个
           appearCount[c-'A']--;
        //
           while(hashSet.contains(s.charAt(j)-'A')&& appearCount[s.charAt(j)-'A']<0){ //
               c=s.charAt(j);
               appearCount[c-'A']++;
               j++;
           }
           if(count==tCount){
               if(res==null || res.length()>i-j+1){
                   res=s.substring(j,i-j+1);
               }
           }
       }
        return res;

    }
    }

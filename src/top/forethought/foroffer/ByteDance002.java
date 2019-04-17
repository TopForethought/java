package top.forethought.foroffer;

import java.util.Scanner;
//  题目描述


// 简单的 单词纠正器
// 1. 三个相同字母连续出现,保留两个,比如 helllo   ->hello
//2. 两对出现,第二对只保留一个  比如   AABB->AAB
//  注意:多对出现,AABBCC->AABCC

public class ByteDance002 {
    public static void main(String[] args) {
        //"abbccddd"
        System.out.println(correctWord("abbcccd"));// abbcdd
        //abbcccd
        //
    }

    public static String correctWord(String input) {
        char[] chars = input.toCharArray();
        StringBuilder result = new StringBuilder();
        int start = 0;
        int end = 1;
        while (end < input.length()) {
            // start 与end  不同
            if (chars[start] != chars[end]) {
                //   出现end  字符不同于start 字符
                // 1. AB 情况  保留start
                if (end - start == 1) {
                    result.append(chars[start]);
                    start = end;
                    end++;
                    continue;
                }
                // 2. AAB
                // 保存start,
                // end-1 能否保存,还得看   end,end+1 是否相同
                // A   A   B           A A B B   或者是 A A B  或者是 A A B C
                // s        e          s   e

                // AAAB    AAABB
                if (end - start >= 2) {
                    result.append(chars[start]);
                    result.append(chars[end - 1]);
                    //  AABB   不保存 end-1
                    //  AAABB
                    if (end + 1 < chars.length && chars[end + 1] == chars[end]) {

                       result.append(chars[end]);
                        start = end;
                        end=start+1;
                    }
                    //AABC
                    else if (end + 1 < chars.length && chars[end + 1] != chars[end]) {
                        start = end;
                        end++;
                        //AAB(结束)
                    } else {
                        result.append(chars[end]);
                        break;
                    }
                }
                continue;
            }
            // start  与end  相同
            if (chars[end] == chars[start]) {
                end++;

            }


        }
        return result.toString();

    }
}

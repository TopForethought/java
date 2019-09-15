package top.forethought.foroffer.acwing.str_week4;
/**
 * author : wangwei
 * description: 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 * date:      2019/8/28
 */

// 分析：最长公共前缀，那么最长也不会超过最短字符串
    // 方式1： 枚举 第0个字符串每一位，然后遍历每个其他字符串的相应位置 时间复杂度 O（m*n）
    // 方式2： 二分法，对第0个字符串二分，然后递归检测
    // 比如  str[0,j] 与其他字符串相比较，如果公共前缀就是str[0,j]   那么就的进一步判断 str[j+1,(j+1+right)/2] 与其他字符串 temp[j-1,lenght] 之间的最长前缀
    // 如果有，则拼接，并继续递归下去
public class Leetcode14LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
             if(strs==null || strs.length==0){
                 return  "";
             }
             String maxPrefix=strs[0];
             int compareIndex=0;
             while (compareIndex<maxPrefix.length()){
                 for(int i=1;i<strs.length;i++){ // i  表示比较的第i 个字符串
                      if(strs[i].length()<=compareIndex||strs[i].charAt(compareIndex)!=maxPrefix.charAt(compareIndex)){
                          return maxPrefix.substring(0,compareIndex);
                      }
                 }
                 compareIndex++;
             }
        return maxPrefix.substring(0,compareIndex);
    }

    public static void main(String[] args) {
        new Leetcode14LongestCommonPrefix().longestCommonPrefix(new String[]{"aa","a"});
    }
}

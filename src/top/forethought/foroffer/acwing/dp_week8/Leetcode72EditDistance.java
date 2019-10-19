package top.forethought.foroffer.acwing.dp_week8;

import static java.lang.Math.min;

/**
 *  编辑距离
 *  给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Leetcode72EditDistance {
    public int minDistance(String word1, String word2) {
       int [][]dp=new int[word1.length()+1][word2.length()+1];
       // 处理边界
        // 第一个字符串的前i 个字母转为 第2个字符串的第0个字母（需要删除 i）
        for(int i=0;i<=word1.length();i++){
            dp[i][0]=i;
        }
        // 第一个字符串第0个字符转为 第2个字符串的第i个字母（需要增加 i）
        for(int i=0;i<=word2.length();i++){
            dp[0][i]=i;
        }

        for(int i=1;i<=word1.length();i++){
           char a=word1.charAt(i-1);
            for(int j=1;j<=word2.length();j++){

               char b=word2.charAt(j-1);
               // 替换 相同就不替换
                // 不同就替换一次

                   dp[i][j]=dp[i-1][j-1]+(a==b?0:1);

               // 删除
                dp[i][j]= min(dp[i-1][j]+1,dp[i][j]);
                // 增加
                dp[i][j]=min(dp[i][j-1]+1,dp[i][j]);
           }
       }
        return dp[word1.length()][word2.length()];
    }
}

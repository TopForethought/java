package top.forethought.foroffer.acwing.dp_week8;

import java.util.ArrayList;
import java.util.List;
// 三角形最小路径之和
public class Leetcode120 {
    public int minimumTotal(List<List<Integer>> nums) {
        int n=nums.size();
        // 1
        // 2 3
        // 4 5 6
        int [][]dp=new int[2][n+1];//滚动数组
        for(int i=1;i<=nums.size();i++){
            List<Integer>row=nums.get(i-1);
            for(int j=1;j<=row.size();j++){
                // 当前点在第一行（那就是本身）
                // 第一行取自己
                // 第一列 取右上
                if(i==1||j==1){
                    dp[i&1][j]=dp[i-1 & 1][j]+row.get(j-1);
                }
                else if(j<row.size()){ //不是最后一列
                    // 其余位置取左上，右上较小值
                    int left=dp[i-1 &1][j-1]+row.get(j-1);
                    int right=dp[i-1 &1][j]+row.get(j-1);
                    dp[i&1][j]=left<right?left:right;
                } else{ // 最后一列 取左上
                    dp[i&1][j]=dp[i-1&1][j-1]+row.get(j-1);
                }
                //  System.out.println(i+" "+j+" :"+dp[i][j]);
            }
        }
        //取最后一层最小值
        int min=dp[n&1][1];
        for(int j=1;j<=n;j++){
            min=min<dp[n&1][j]?min:dp[n&1][j];
            //  System.out.println(dp[n][j]);
        }
        return min;
    }


    public static void main(String[] args) {
        //[
        //     [2],
        //    [3,4],
        //   [6,5,7],
        //  [4,1,8,3]
        //]
        List<List<Integer>> input=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        list1.add(2);
        input.add(list1);
        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        input.add(list2);
        List<Integer> list3=new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        input.add(list3);
        List<Integer> list4=new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        input.add(list4);


        System.out.println(new Leetcode120().minimumTotal(input));
    }
}

package top.forethought.practice.dp;

import top.forethought.common.utils.RandomUtil;

/**
 * @author  wangwei
 * @date     2019/3/26 22:30
 * @classDescription  求最大乘积子序列(连续)
 * 比如:  2,3,-2,4
 *  输出:6=2*3
 *
 *  思路:
 *  1.暴力求解:递归  2^n   ,在递归过程中需要保存全局的max
 *  2.dp
 *    状态定义: dp[i] 记录以 元素 i 结尾 的子序列的最大乘积
 *    状态转移方程: dp[i+1]=max{array[i+1],dp[i]*array[i+1]}???
 *     上述定义是否存在问题???
 *     比如输入:1,2,-3,5,-2  那么最大的乘积应该是全部相乘
 *     如果依赖上述的状态定义
 *     那么:dp[0]=1
 *          dp[1]=2
 *          dp[2]=-3
 *          dp[3]=5
 *          dp[4]=-2
 *          最终得到的最大值是-2显然不是最终需要的答案
 *
 *       思考:产生最大值的情况(两数相乘)  同负数,同正数
 *       dp 定义  dp[i][2]   dp[i][0] 表示以i为序列结尾的最大乘积
 *                            dp[i][1] 表示以i为序列结尾的最小乘积(可能为负)
 *        转移方程:
 *       array[i+1] 为正数
 *        dp[i+1][0]=max{dp[i][0]*array[i+1],array[i+1]}
 *        dp[i+1][1]=min(dp[i][1]*array[i+1],array[i+1]}
 *      array[i+1] 为负数
 *       dp[i+1][0]=max{dp[i][1]*array[i+1],array[i+1]}
 *        dp[i+1][1]=min(dp[i][0]*array[i+1],array[i+1]}
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
        //int []array= RandomUtil.randomInts(-10,10,8);
        int []array={0, -6 ,-4, 9, -7, -1 ,10, 4 };
        RandomUtil.printArray(array);
        System.out.println("最大子序列乘积:"+new MaxProductSubarray().solution(array));
        System.out.println("最大子序列乘积:"+new MaxProductSubarray().solutionRecursion(array,array.length-1));
    }

    public  int solution(int []array){
        int [][]dp=new int[array.length][2];
        dp[0][0]=array[0];
        dp[0][1]=array[0];
        for(int i=1;i<array.length;i++){
            int item=array[i];
            if(item>0){
                dp[i][0]=max(dp[i-1][0]*item,item);
                dp[i][1]=min(dp[i-1][1]*item,item);
            }else {
                dp[i][0]=max(dp[i-1][1]*item,item);
                dp[i][1]=min(dp[i-1][0]*item,item);
            }
        }
        return dp[array.length-1][0];
    }


    // 递归实现
    // 求以end结尾的最大子序列之乘积(暂时未实现)
    public int solutionRecursion(int array[],int end){
        if(end==0){
            return array[end];
        }

        int leftProduct=solutionRecursion(array,end-1);// 以i-1 结尾的子序列最大乘积
        int maxTemp=max(leftProduct,array[end]*leftProduct);
        int maxmax=max(maxTemp,array[end]);// 三者之中最大的
        int minTemp=min(leftProduct,array[end]*leftProduct);
        int minmin=min(minTemp,array[end]);// 三者之中最小的
        return max(maxmax,minmin);
    }
    private int max(int a,int b){
        return a>b?a:b;
    }
    private int min(int a,int b){
        return a<b?a:b;
    }
}

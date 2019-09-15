package top.forethought.foroffer.acwing;


//有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
//
//        第 i 件物品的体积是 vi，价值是 wi。
//
//        求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
//        输出最大价值。

import top.forethought.concurrency.threads.collections.map.HashMaoDemo;

import java.util.*;

import static java.lang.Math.max;

public class BackPack01 {
    public static void main(String[] args) {
        int N;
        int V;
        Scanner scanner=new Scanner(System.in);
        N=scanner.nextInt();//物品数量
        V=scanner.nextInt();//背包容量
        int [][]goods=new int[N+1][2];
        for(int i=1;i<=N;i++){
            goods[i][0]=scanner.nextInt();//读入体积
            goods[i][1]=scanner.nextInt();//读入价值
        }
        System.out.println(solution(V,N,goods));
    }

    public static int solution(int V,int N,int [][]goods){
      int [][] dp=new int[N+1][V+1];// dp[i][j] 表示背包体积为j 的情况下,
                             // 放i 件货物得到的最大价值
        for(int i=1;i<goods.length;i++){//物品

           for(int j=V;j>0;j--){// 体积
               // 对于第i 件商品 ,如果第i 件商品体积过大,无法放下,那么dp[i][j]=dp[i-1][j-1]
               if(goods[i][0]>j){
                   dp[i][j]=dp[i-1][j];// 状态和放i-1 件商品一致
                   continue;
               }
               // 如果能放下,需要判断最最优解
               else{
                   dp[i][j]=max(dp[i-1][j],dp[i-1][j-goods[i][0]]+goods[i][1]);
               }
           }
        }
        return dp[N-1][V-1];
    }
}

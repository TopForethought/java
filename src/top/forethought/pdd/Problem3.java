package top.forethought.pdd;

import java.util.Scanner;
import java.util.Stack;
// 已经 t 次没找到（连续）
// n  共需要找的 1
//m 已找到的 0
// k  保底机制触发机制 50
// x 基本概率 0.02
//          y=0.02
// 假设 某次未找到，那么  期望次数+=（1-p）
//  某次找到   期望次数+=1

public class Problem3 {
// n，m,t,k,x,y
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int t=in.nextInt();
        int k=in.nextInt();
        double x=in.nextDouble();
        double y=in.nextDouble();
          System.out.println(solution(n,m,t,k,x,y));
        in.close();
    }
// 第 w 次能找着的期望是  之前都是寻找失败了
    //
    private static double solution(int n, int m, int t, int k, double x, double y) {

        int target=n-m;// 还需要找这么多
        int times=0;
        double res=0;
        while (target>0){
            // 已经连续t 次没找到
           double p=x;
            if(t>k){
                p=(t-k)*y;// 触发保底机制
            }
            if(p>=1){
                target--;// 保底成功
                t=0;
            }else{ // 保底失败
                t++;// 连续失败次数
            }
            // 这次能够找到
            times++;
            res+= times*p;
        }
        return res;

    }

}

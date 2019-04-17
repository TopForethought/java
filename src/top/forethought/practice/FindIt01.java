package top.forethought.practice;

import java.util.Scanner;
// 代码未成功,  一个人在起点到终点

// 已知起点和终点坐标,可以8个方向前进,给定  总前进步数k,
//  判断能否到达 终点
// 如果能到达,输出  最多能走的斜线次数
public class FindIt01 {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            while (in.hasNextInt()) {//注意while处理多个case
                int caseNum = in.nextInt();
                while(caseNum-->0){
                    int x=in.nextInt();
                    int y=in.nextInt();
                    int k=in.nextInt();// 总前进步数
                    x=(x>0?x:-1*x);
                    y=(y>0?y:-1*y);
                    int maxStep=x+y;
                    int  max=x>y?y:x;// max 表示斜线次数,一次斜线会省1步
                    int minStep=maxStep-max;
                    int result=-1;

                    if(k<minStep){
                        result=-1;
                    }
                    else{
                        result=maxStep-k;
                    }
                    System.out.println(result);

                }

            }
        }
    }


package top.forethought.practice;

import java.util.Scanner;

/**
 *  地图中,只能向右,向下前进,求路径数
 *
 */
public class Test03030 {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while (in.hasNextInt()){
            int a=in.nextInt();
            int b=in.nextInt();
            System.out.println(solution(a,b));
        }
    }

    // a=x
    // b=y
    public static int solution(int a,int b){
        int [][]map=new int[b][a];

        // 第0行初始化为1
        for(int i=0;i<a;i++){
          map[0][i]=1;
        }
        //第0 列初始化为1
        for(int i=0;i<b;i++){
            map[i][0]=1;
        }
        for(int i=1;i<b;i++){
            for(int j=1;j<a;j++){
                map[i][j]=map[i-1][j]+map[i][j-1];
            }
        }

         return map[b-1][a-1];


    }

}

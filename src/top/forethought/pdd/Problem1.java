package top.forethought.pdd;

import java.util.Scanner;

public class Problem1 {
static int minPos=-1;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T=in.nextInt();
        int [][]input=new int[T][2];
        for(int i=0;i<T;i++){
            minPos=Integer.MAX_VALUE;
            int a=in.nextInt();
            int b=in.nextInt();
            input[i][0]=a;
            input[i][1]=b;
        }
        for(int i=0;i<T;i++){
            minPos=Integer.MAX_VALUE;
            solution(input[i][0],input[i][1],0);
            System.out.println(minPos==Integer.MAX_VALUE?-1:minPos);
        }

    }
    //k 当前第几步
    public  static int solution(int a,int b,int k){
           if(a==b){
               minPos=k>minPos?minPos:k;
               return k;
           }
           if(a>b){
               return -1;
           }
           if(a*2==b){
               return k+1;
           }

           // 选择 *10+1
        // 枚举 每一步的是使用 方法，在最终求的结果的步骤中选取步数最少的
        // 选择乘2
         int res=solution(a*2,b,k+1);
           if(res>=0){
               minPos=res>minPos?minPos:res;
               return res;// 当前选择 *2 可行
           }else{
               // 选择 *10+1
               res=solution(a*10+1,b,k+1);
               if(res>=0){
                   minPos=res>minPos?minPos:res;
                   return res;// 当前选择 *2 可行
               }
           }


        return -1;
        }
}

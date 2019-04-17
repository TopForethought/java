package top.forethought.practice;

import java.util.Scanner;

// 求连续自区间和小于给定值的区间个数
// 区间内的数都是正数
public class VirtualYouTober {
    public static void main(String[] args) {

        Scanner in=new Scanner(System.in);

        int testCases= Integer.parseInt(in.nextLine());
        int count=0;

        while (testCases-->0){
            count++;
            int sum=Integer.parseInt(in.nextLine().split(" ")[1]);
            String [] datas=in.nextLine().split(" ");
            System.out.println("Case #"+ count+": "+solution(datas,sum));
        }

    }
    static int solution(String [] datas,int sum){
//        int [][]dp=new int[datas.length][datas.length];
//     int count=0;
//       for(int i=0;i<datas.length;i++){
//           dp[i][i]=Integer.parseInt(datas[i]);
//           if(dp[i][i]<sum){
//               count++;
//           }
//       }
//       for(int i=0;i<datas.length;i++){
//           for(int j=i+1;j<datas.length;j++){
//               dp[i][j]=dp[i][j-1]+dp[j][j];
//               if(dp[i][j]<sum){
//                 count++;
//               }
//           }
//       }
        int count=0;
        int left=0;
        int right=0;
        int tempSum=0;
        for(left=0;left<datas.length;left++){

        }
        while(left<datas.length){

                tempSum+=Integer.parseInt(datas[right]);
            if(tempSum>=sum){
                tempSum-=Integer.parseInt(datas[left]);
                left++;
            }else {
                if(right<datas.length-1){
                    right++;
                }else {
                    left++;
                }

                count++;
            }
        }
       return count;
    }
}

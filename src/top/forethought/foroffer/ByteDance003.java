package top.forethought.foroffer;

import java.util.Scanner;

/**
 * @author wangwei
 * @date 2019/3/16 15:22
 * @classDescription 问题描述:n 人参赛,每人有一个得分,
 * 围成一圈,如果得分比相邻的人高,则奖品数也得比邻居高
 * 求主办方最少准备的奖品数是多少?
 * <p>
 * 分析:每人至少1件礼物,需要的是总数,那么就可以  遍历一圈,如果比左边高,总数+1,比右边高,总数+1
 * 总数初始值为参赛人数
 * <p>
 * 输入:得分    1    3  3  (输出5)
 * 得分   1 2  (输出3)
 */
public class ByteDance003 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int caseCount = in.nextInt();
            while (caseCount-- > 0) {
                int people = in.nextInt();
                int[] grades = new int[people];
                while (--people >= 0) {
                    grades[people] = in.nextInt();

                }
                System.out.println(findGifts(grades));
            }


        }
    }

    public static int findGifts(int grades[]) {
        int sum = grades.length;
//      for(int i=0;i<grades.length;i++){
//
//          for(int j=0;j<grades.length;j++){
//
//              if(j==i){
//                  continue;
//              }
//              if(grades[i]>grades[j]){
//                  sum++;
//              }
//          }
//
//      }

        int count = 1;//记录比较的次数,n 次
        while (count < grades.length) {
            int left = (count - 1+grades.length) % grades.length;
            int right = (count + 1+grades.length) % grades.length;
            if (grades[count] > grades[left]) {
                sum++;
            }
            if (grades[count] > grades[right]) {
                sum++;
            }
            count++;
          }
          // 现在缺少  最后一个位置和
          // 注意最后一个位置大于第一个位置的情况:
        return sum;
    }
}

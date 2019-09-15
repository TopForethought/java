package top.forethought.foroffer;

import java.util.Scanner;
// 阿里笔试题，深搜竟然没有过样例！！！！！

public class Main {





        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            String line = scanner.nextLine();
            int n = Integer.parseInt(line);
            int[][] area = new int[n][n];

            for (int i = 0; i < n; i++) {
                line = scanner.nextLine();
                String[] split = line.split(",");
                if (split.length != n) {
                    throw new IllegalArgumentException("错误输入");
                }
                int j = 0;
                for (String num : split) {
                    area[i][j++] = Integer.parseInt(num);
                }
            }

            int minimumTimeCost = getMinimumTimeCost(n,area);
            System.out.println(minimumTimeCost);
        }
        static int minTime=10000;// 全局保存最短时间
        /** 请完成下面这个函数，实现题目要求的功能 **/
        /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
        private static int getMinimumTimeCost(int n, int[][] area) {
            int currTime=0;
            minTime=10000;
            boolean jump=false;
            // 深度优先搜索，搜索到尽头，比较最小值
            dpSearch( area,jump,n,0,0,1,0,currTime);
            dpSearch( area,jump,n,0,0,0,1,currTime);
            return minTime;
        }
        static void dpSearch(int[][]area,boolean jump,int n,int curX,int curY,int dx,int dy,int currTime){
            //到达最下，比较此次搜索结果
            if(curY==n-1){
                minTime=minTime<currTime?minTime:currTime;
                return ;
            }
            // 如果当前点是 跳跃，那么不算时间
            // 否则加上该点时间
            if(!jump){
                currTime+=area[curX][curY];
            }
            jump=!jump;

            if(curX+dx==n){// 右边界出界，往下搜
                dpSearch( area,jump,n,curX,curY,0,1,currTime);
                return;
            }

            // curx+dx,curY+dy
            // 其余选择 往右，选择往下
            curX+=dx;
            curY+=dy;
            dpSearch( area,jump,n,curX,curY,1,0,currTime);
            dpSearch( area,jump,n,curX,curY,0,1,currTime);
        }
}
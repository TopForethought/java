package top.forethought.foroffer;


import java.util.ArrayDeque;
import java.util.Queue;

//地上有一个m行和n列的方格。
//        一个机器人从坐标0,0的格子开始移动，
//        每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
//        例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
//        请问该机器人能够达到多少个格子？
public class RobotWalk {
    public static void main(String[] args) {
       RobotWalk walk =   new RobotWalk();
      System.out.println(walk.movingCount(3,13,14));
    }

// 使用宽度优先搜索
    // 如果周围的点能够访问就将其加入队列
    // 直到队列为空

    // 思路：将能到达的点放入队列，取队列中元素，依次判断此元素四周能否到达
    // 如果能到达，就将其加入队列
    public int movingCount(int threshold, int rows, int cols)
    {
       boolean [][]visited=new boolean[rows][cols];// 记录该点是否已经访问
        //记录到达的点的数量
        int sum=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(visited[i][j]||bitSum(i)+bitSum(j)>threshold){
                    continue;
                }
//                if(bitSum(i)+bitSum(j)>threshold){
////                    visited[i][j]=true;
////                    continue;
////                }
                visited[i][j]=true;
                sum=sum+1+sumAround(i,j,rows,cols,threshold,visited);
            }
        }
        return sum;
    }

    //此方法判断周围四个位置是否可达
    private int sumAround(int i, int j, int rows, int cols, int threshold,boolean[][] visited) {
        int sum=0;
        int dx[]={1,0,-1,0};
          int dy[]={0,1,0,-1};
          for(int m=0;m<4;m++){
            int x=j+dx[m];// 列
            int y=i+dy[m];// 行
              if(x<0||x>=cols||y<0||y>=rows||visited[y][x]||bitSum(x)+bitSum(y)>threshold){
                  continue;
              }
              visited[y][x]=true;
              sum++;
          }
          return sum;

    }


    static int bitSum(int num){
        int res=0;
        do{
            res+= (num%10);
        }while((num/=10)>0);
        return res;
    }




}

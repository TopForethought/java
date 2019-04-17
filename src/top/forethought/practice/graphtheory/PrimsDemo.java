package top.forethought.practice.graphtheory;

import java.util.Scanner;

/**
 * @author  wangwei
 * @date     2019/4/9 10:09
 * @classDescription
 *  最小生成树:prim's算法,基于切分性质(贪心算法)
 *  //  V 顶点集,E边集
 *   令S 是顶点集合任意子集,并令e是具有最小权重的边,该边的一个节点在S中
 *   另一个在V-S中,则最小生成树必定包含e
 *
 * 思路:
 * 点全集为  S
 * 1,最小生成树中选入1点  ,已选入点集合记为A,则未选入点集合为  S-A
 * 2,  对于A 任意点x1,与S-A中任意点x2之间求得  边长最小的点x22 ,将x22加入 A,并从 S-A 中移出
 * 3,直到 S-A 中为无点时,完成最小生成树
 *
 *
 *   时间复杂度:与边无关
 *   o(n*n) ,适合求稠密矩阵
 */
public class PrimsDemo {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNextLine()){
            int vCount=Integer.parseInt(in.nextLine());
            int [][]graph=new int[vCount][vCount];
            // 初始化
            int defaultVal=Integer.MAX_VALUE;
            memeset(graph,defaultVal);
            // 读取数据  格式是 x,y,val
            String data=in.nextLine();
            String [] datas=data.split(",");
            int x=0;
            int y=0;
            int val=0;
            for(int i=0;i<datas.length;i+=3){
                x=Integer.parseInt(datas[i]);
                y=Integer.parseInt(datas[i+1]);
                val=Integer.parseInt(datas[i+2]);
                graph[x-1][y-1]=val;
                graph[y-1][x-1]=val;
            }

         int [][]result= solution(graph);
          showArray(result);
        }
    }

    private static int[][] solution(int[][] graph) {
      int [][]result=new int[graph.length][graph[0].length];
      boolean []selected=new boolean[graph.length];
      memeset(result,-1);
      // 算法思路:
        // result 记录选入最小生成树的顶点以及边,直到所有顶点都被选入
        // 设S =graph-result
        // 对任意点 p属于S, 找到该点与已选择点之间形成的最小边加入result
        selected[0]=true;
        for(int i=0;i<selected.length;i++){
            int minDis=Integer.MAX_VALUE;//
            int startPoint=-1;
            int minPoint=-1;

            for(int j=0;j<selected.length;j++){
                if(!selected[j]){
                    continue;
                }

                for(int k=0;k<selected.length;k++){

                    if(selected[k]){
                        continue;
                    }
                    // 现在是  j 是已选入的顶点
                    // k 是未选入的顶点
                    if(graph[j][k]<minDis){
                        startPoint=j;
                        minDis=graph[j][k];
                        minPoint=k;
                    }
                }


            }
            //至此已经找到最小边加入结果集中
            if(minPoint!=-1){
                result[startPoint][minPoint]=graph[startPoint][minPoint];
                System.out.println("选入:"+(startPoint+1)+"----"+(minPoint+1));
                selected[minPoint]=true;
            }
        }

        return result;
    }

    private static void memeset(int[][] graph, int defaultVal) {
    for(int i=0;i<graph.length;i++){
        for(int j=0;j<graph[i].length;j++){
            if(i!=j){
                graph[i][j]=defaultVal;
            }
        }
    }


    }
    private static void showArray(int [][]data){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    }
}

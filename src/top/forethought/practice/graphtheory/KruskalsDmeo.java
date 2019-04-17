package top.forethought.practice.graphtheory;

import java.util.*;


// 代码暂时未实现
/**
 * @author  wangwei
 * @date     2019/4/9 11:12
 * @classDescription
 * Kruskal's 算法求最小生成树
 *   初始将各点看作是孤立的,不连通的
 * 逻辑:
 * 1.将边按照从小到大顺序排序
 * 2,对于边(u,v),如果端点u和v在同一连通分支 中,跳过判断下一条边
 * 3  对于边(u,v) 如果u,v 不在同一连通分支,则将连通分支合并,接着判断下一条边
 * 4,当只剩1个连通分支时,得到最小生成树
 * 时间复杂度:   o(elge)  //e 是边的条数(这是排序的复杂度)
 * 适合求稀疏矩阵
 *
 */
public class KruskalsDmeo {


    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNextLine()){

        List<Edge> edgeList=new ArrayList<>();
            // 读取数据  格式是 x,y,val
            String data=in.nextLine();
            String [] datas=data.split(",");
            for(int i=0;i<datas.length;i+=3){
                Edge edge=new Edge();
                edge.point1=Integer.parseInt(datas[i]);
                edge.point2=Integer.parseInt(datas[i+1]);
                edge.disitance=Integer.parseInt(datas[i+2]);
                edgeList.add(edge);
            }
            Collections.sort(edgeList);
            System.out.println(edgeList.toString());
       List<Edge>  result=solution(edgeList);
       System.out.println(result.toString());
        }
    }

    private static List<Edge> solution(List<Edge> edgeList) {
     List<Edge> result=new ArrayList<>();
     Set<Integer> selectedPoint=new HashSet<>();
      for(Edge edge:edgeList){
          // 如果顶点都被选择了,则跳过该条边
          // 否则加入该条边
          if(result.contains(edge)){
              System.out.println("edge 重复选择:"+edge.toString());
              continue;
          }
          // 两个顶点都被选入了
          if(selectedPoint.contains(edge.point1)&&selectedPoint.contains(edge.point2)){
              continue;
          }
          result.add(edge);
          System.out.println("加入边:"+edge.toString());
      }

        return result;

    }


    // 边,端点,边长
static  class Edge implements Comparable{
    int point1;
    int point2;
    int disitance;

        @Override
        public int compareTo(Object o) {
          Edge obj=(Edge)o;
            if(this.disitance<obj.disitance){
                return -1;
            }
            if(this.disitance==obj.disitance){
                return 0;
            }
            return 1;

        }

        @Override
        public String toString() {
            return "Edge{" +
                    "point1=" + point1 +
                    ", point2=" + point2 +
                    ", disitance=" + disitance +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return (point1 == edge.point1 &&
                    point2 == edge.point2  || point1 == edge.point2 &&
                    point2 == edge.point1)  &&
                    disitance == edge.disitance;
        }

        @Override
        public int hashCode() {

            return Objects.hash(point1, point2, disitance);
        }
    }
}

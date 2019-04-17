package top.forethought.huawei;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private static HashSet<Integer> arrived = new HashSet<>();// 标记到过的点的编号

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String datas = in.nextLine();
            System.out.println(solution(datas));
        }
    }

    public static int solution(String datas) {

        String[] array = datas.split(" ");
        int[] position = convertString2Int(array);
        int width = position.length / 2;
        double[][] map = new double[width][width];// n*n 矩阵保存距离关系
        // map[i,j] 初始化存i 到j 的距离,后序搜索过程会更新
        for (int i = 0; i < width; i++) {
            int x1 = position[2 * i];
            int y1 = position[2 * i + 1];
            for (int j = i; j < width; j++) {
                int x2 = position[2 * j];
                int y2 = position[2 * j + 1];
                map[i][j] = calDist(x1, y1, x2, y2);
                map[j][i] = map[i][j];
            }
        }
        // 处理距离
        // 先确定距离原点最近的点
        // 应该需要依次确定点的访问顺序
        double minDist = 0;
        int minDistIndex;// 与当前点距离最近的点编号
        int currIndex = 0;//当前起点位置编号
        for (int count = 0; count < width - 1; count++) {
            minDistIndex = getMindDist(currIndex, map);
            minDist += map[currIndex][minDistIndex];
            currIndex = minDistIndex;
        }
        // 加上终点到原点的距离
        minDist += map[0][currIndex];

        return (int) minDist;
    }

    //字符串转为数字数组,注意为了方便处理,原点也加入
    public static int[] convertString2Int(String[] array) {
        int[] intArray = new int[array.length + 2];
        for (int i = 2; i < array.length + 2; i++) {
            intArray[i] = Integer.parseInt(array[i - 2]);
        }
        return intArray;
    }

    //计算距离
    public static double calDist(int x1, int y1, int x2, int y2) {
        int dX = x1 - x2;
        int dY = y1 - y2;
        return Math.pow(dX * dX + dY * dY, 0.5);
    }

    //返回距离start 最近的点的坐标
    public static int getMindDist(int start, double[][] map) {
        double minDist = Integer.MAX_VALUE;
        int minDisPointIndex = -1;//离start 点最近的点坐标(或者说是编号)
        for (int i = 0; i < map.length; i++) {
            // 编号曾经到过就不再使用
            if (i == start || arrived.contains(i)) {
                continue;
            }
            if (map[start][i] < minDist) {
                minDist = map[start][i];
                minDisPointIndex = i;
            }
        }
        // 注意将选取的点那一行给标记为去过,否则会出现重复查找
        arrived.add(minDisPointIndex);
        return minDisPointIndex;
    }
}



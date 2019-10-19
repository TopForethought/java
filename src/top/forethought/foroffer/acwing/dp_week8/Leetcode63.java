package top.forethought.foroffer.acwing.dp_week8;

public class Leetcode63 {
    public int uniquePathsWithObstacles(int[][] map) {
        // 从最后一个点开始思考
        // map[i][j] 可能是从从上往下到达， dp[i][j]=dp[i-1][j-1]
        // 也可能是从左往右到达        dp[i][j]=dp[i][j-1]
        //
        // 如果两个方向都可以到达，那么就是 二者之和
        // 障碍物是1
        // 空位置是 0
        int row=map.length;
        int col=map[0].length;
        int [][] dp=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                // 如果当前点为石头
                if(map[i][j]==1){
                    dp[i][j]=0;
                    continue;
                }
                if(i==0 && j==0){
                    dp[i][j]=map[i][j]==1?0:1;
                    continue;
                }
                // 如果是第0 行，以左边为准
                if(i==0){
                    dp[i][j]=dp[i][j-1];
                    continue;
                }
                // 第0列 只能是 以上边为准

                if(j==0 ){
                    dp[i][j]=dp[i-1 ][j];
                    continue;
                }
                // 其余位置 以 左+上
                dp[i][j]=dp[i][j-1]+dp[i-1 ][j];

            }
        }
        return dp[row-1][col-1];
    }
}

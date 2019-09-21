package top.forethought.foroffer.acwing.dfs_week5;

import java.util.Scanner;

/**
 * 数独问题：
 * 每一个3*3 的格子里的数字，满足 1-9 的数字只使用一次
 * 在每一行 1-9 的数字最多出现一次
 * 每一列也是如此
 * <p>
 * 给定的数独序列只包含1-9 和字符 ‘.’ (代表未填入）
 * 可以假定给定的数独只有唯一解
 * 给定数独永远是9*9 形式的
 * <p>
 * 思路：重点是搜索顺序
 * 从前往后 枚举每个空格该填哪个数
 */

public class Leetcode37SudokuSolver {

    //
    boolean[][]   row;
    boolean[][]  col;
    boolean[][][] cell;

    public void solution(char[][] board) {
        row   = new boolean[9][9];//  row[i][j]=true 表示第 i 行 填了 j
        col     = new boolean[9][9];// col[i][j] =true 表示第i列填了 j
        cell = new boolean[3][3][9];// 记录 那9个3*3 矩阵 cell[i][j][k] =true 某个 3*3 矩阵填上了数字 k

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];// 如果是填过的，将其记录在 状态数组中
                if (board[i][j] != '.') {
                    row[i][c - '1'] = true;
                    col[j][c - '1'] = true;
                    cell[i / 3][j / 3][c - '1'] = true;
                }
            }
        }
        dfs(board, 0, 0);

    }

    // 第 x 行 y 列
    private boolean dfs(char[][] board, int x, int y) {
        if (y == 9) { // 换行
            x++;
            y = 0;
        }
        if (x == 9) { // 最后一行已经完成，所有数据已经填写完成
            return true;
        }
          // 如果已经填写过，去下一个位置
        if(board[x][y]!='.'){
            return dfs(board,x,y+1);
        }
        // 没有填写过  枚举该位置可以填写哪些数
        for(int i=0;i<9;i++){
            if(!row[x][i] && !col[y][i] && !cell[x/3][y/3][i]){
                row[x][i]=col[y][i]=cell[x/3][y/3][i]=true;
                board[x][y]=(char)(i+'1');// 填入数字
                if(dfs(board,x,y+1)){
                    return true;
                }
                board[x][y]='.';// 还原现场
                row[x][i]=col[y][i]=cell[x/3][y/3][i]=false;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Leetcode37SudokuSolver sudo=new Leetcode37SudokuSolver();
        Scanner in=new Scanner(System.in);
        char [][]board=new char[9][9];
        while (true){
            for(int i=0;i<9;i++){
                System.out.println("请输入第 "+(i+1)+"行");
                String line=in.nextLine();
                for(int j=0;j<9;j++){
                    board[i][j]=line.charAt(j);
                }
            }
            sudo.solution(board);
            showResult(board);
        }
    }

    private static void showResult(char[][] board) {

      for(int i=0;i<board.length;i++){
          System.out.println("第 "+(i+1)+"行");
          for(int j=0;j<board[i].length;j++){
              System.out.print(board[i][j]+" ");
          }
          System.out.println();
      }

    }

}

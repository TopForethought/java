package top.forethought.foroffer.acwing.dfs_week5;

import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;

/**
 *  给定一个  字符构成的二维数组,输入一个单词,判断数组中是否存在该单词
 *  (四联通)
 *  思路:枚举字符,如果与单词首字母相同,继续枚举字符周围(四个方向的字母) 去匹配
 *               DFS   // 存在问题
 */

public class Leetcode79WordSearch {
    int width;
    int height;
    int []dx={0,1,0,-1};// 向右,向下，向左，向上
    int []dy={1,0,-1,0};
    public boolean exist(char[][] board, String word) {
        width=board[0].length;
        height=board.length;
   for(int i=0;i<height;i++){
       for(int j=0;j<width;j++){
           if(dfs(board,word,i,j,0)){
               return true;
           }
       }
   }
        return false;
    }
    // 以   board的 第 [x][y] 元素作为 单词的开头去匹配 单词的第index 个字母
    private boolean dfs(char[][] board, String word, int x,int y,int index ) {

        if(board[x][y]!=word.charAt(index)){ // 当前字符匹配失败,(可能是匹配失败，也可能该点已经被访问）
            return false;
        }
        if(index==word.length()-1){
            return true;// 已经完成匹配
        }

        // 标记当前位置
        board[x][y]='*';   // 标记
        // 继续匹配其他方向
        for(int i=0;i<4;i++){
            if(indexValid(x+dx[i],y+dy[i]) ){// 如果当前访问点坐标不合法,或者是已经被访问过,则跳过该点
                    if( dfs(board,word,x+dx[i],y+dy[i],index+1)){
                        return true;
                    }
            }

        }
        // 还原当前位置
       board[x][y]=word.charAt(index);// 复位
        return false;
    }

  private   boolean  indexValid(int x,int y){
      if(x<0||x>=height||y<0||y>=width){
          return false;
      }
      return true;
  }

    public static void main(String[] args) {
//        char [][]board={{'A','B','C','E'},
//                        {'S','F','C','S'},
//                        {'A','D','E','E'}};
//        char [][]board={{'A','A'}};
          char [][]board={{'A'}};
         System.out.println(  new Leetcode79WordSearch().exist(board,"A"));
    }
}

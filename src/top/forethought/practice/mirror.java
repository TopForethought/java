package top.forethought.practice;

import java.util.Scanner;

public class mirror {


    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

            String [] strs=in.nextLine().split(" ");
           int n=Integer.parseInt(strs[0]);
           int m=Integer.parseInt(strs[1]);

            char  [] datas=in.nextLine().toCharArray();
            while (m-->0){
              String []tests=in.nextLine().split(" ");
              if(tests[0].equals("1")){
                  datas[Integer.parseInt(tests[1])-1]=tests[2].toCharArray()[0];
              }else{
                  System.out.println(solution(datas,Integer.parseInt(tests[1]),Integer.parseInt(tests[2])));
              }
          }



    }

    // 检查区间镜像
    private static String  solution(char[] datas, int l,int r) {
   //
        int leftIndex=datas.length-r;
        int rightIndex=datas.length-l;
        while (l<r){
            if(datas[leftIndex]!=datas[l]){
                return "NO";
            }
           l++;
            leftIndex++;
        }
       return "YES";
    }

    public static void swap(char [] array,int i,int j){
          char temp=array[i];
          array[i]=array[j];
          array[j]=temp;
    }
}

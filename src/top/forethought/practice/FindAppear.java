package top.forethought.practice;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author  wangwei
 * @date     2019/3/10 21:00
 * @classDescription  N*N 数组,从上往下,从左往右递增,查找出是否存在某个元素
 *
 */
public class FindAppear {

        public boolean Find(int target, int [][] array) {
            int height=array.length;
            int width=array[0].length;
            int j=0;
            boolean find=false;
            for(int i=height-1;i>=0 && (!find) &&(j<width);){
              while (j<width && (!find) && i>=0){
                    if(array[i][j]>target){
                        i--;
                    }else if(array[i][j]<target){
                        j++;
                    }else{
                        find=true;
                    }
                }
            }
            return  find;
        }

    public static void main(String[] args) {
        int [][]array={{1,2,3},{4,5,6},{7,8,9}};
     //  System.out.println(new FindAppear().Find(-1,array));

       System.out.println(replaceSpace(new StringBuffer("HELLO     WORLD  hello")));
    }
    public static  String replaceSpace(StringBuffer str) {
        char []chars=str.toString().toCharArray();
        java.util.List<String> list=new java.util.ArrayList<>();
        for(int i=0;i<chars.length;i++){
            if(chars[i]!=' '){
                list.add(chars[i]+"");
            }
            else{
                if(!"%20".equals(list.get(list.size()-1))){
                    list.add("%20");
                }
            }
        }
        StringBuffer str2=new StringBuffer();
        for(String s:list){
            str2.append(s);
        }
        return str2.toString();
    }
    }


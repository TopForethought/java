package top.forethought.practice;

import top.forethought.common.utils.ArrayUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 返回字符串的所有排列
 * 注意:字符可能重复
 */
public class Permutation {
    public ArrayList<String> Permutation(String str) {
        // 全排列
        // 以某个字符为基准,将后序字符依次与其交换一次,得到的结果存入返回结果
        // 然后将基准字符归还原位,固定下一个字符,将后序字符与其交换一次
        ArrayList<String> arrayList=new ArrayList<>();
      if(null==str||str.length()==0){
          return arrayList;
      }
        char []array=str.toCharArray();
        doRecrusion(array,0,arrayList);
        Collections.sort(arrayList);
        return arrayList;
    }
    //递归调用获取全排列数据
    public void doRecrusion(char[]array,int currIndex,ArrayList<String> result){

          boolean appeared[]=new boolean[26*2];//防止字母重复出现做出重复交换
           if(currIndex==array.length-1){
               result.add(new String(array));
               System.out.println(new String(array));
               return;
           }

        for(int i=currIndex;i<array.length;i++){
            if(appeared[array[i]-'A']){
                continue;
            }
               swap(currIndex,i,array);
        //    result.add(new String(array));
          //  System.out.println(new String(array));
             doRecrusion(array,currIndex+1,result);
            swap(currIndex,i,array);
            appeared[array[i]-'A']=true;
        }


    }
    private void swap(int i,int j,char []array){
        char temp=array[i];
        array[i]=array[j];
        array[j]=temp;

    }

    public static void main(String[] args) {
        String input="abc";

    int a=~1;


    System.out.println(a);
        new Permutation().Permutation(input);
    }
}

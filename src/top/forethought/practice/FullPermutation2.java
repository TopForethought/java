package top.forethought.practice;

import top.forethought.common.utils.ArrayUtil;

/**
 * @author  wangwei
 * @date     2019/1/25 20:43
 * @classDescription  打印n个数的全排列:每个元素都可以放到位置k, k 初始化为0,k之后的元素都可以交换到位置k,
 *                                                每个元素都可以放到k+1,
 *                                                知道k位于最后一个位置,表示已经处理完,可以输出
 *    比如输入 3
 *    输出:1,2,3
 *         1,3,2
 *         2,1,3
 *         2,3,1
 *         3,2,1
 *         3,1,2
 */
public class FullPermutation2 {

    static int swapCount=0;
    static int fullPerCount=0;
    public static void  print(int array[],int k){

        if(k==array.length-1){
            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
            fullPerCount++;
            return;
        }
        //
        boolean[] record=new boolean[10];//记录交换的数据
        for(int i=k;i<array.length;i++){
           if(record[array[i]]){
               continue;//有交换记录,跳过此次交换
           }
            swap(array,k,i);
           swapCount++;
            print(array,k+1);
            swap(array,k,i);// 注意交换后需要还原
            record[array[i]]=true;
        }

    }

    public static void main(String[] args) {
        int [] array=new int[]{1,2,3};

        print(array,0);
        System.out.println(swapCount/fullPerCount%998244353);
    }

public static void swap(int []array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
}
}

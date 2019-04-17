package top.forethought.practice.memory;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.common.utils.RandomUtil;
import top.forethought.sorts.interfaces.Sortable;

public class BubbleSort implements Sortable{
    @Override
    public void sort(int[] array) {


        int lastSwapIndex=array.length-1;
        boolean swapped=true;
        for(int i=0;i<array.length && swapped; i++){
            swapped=false;
            int temp=lastSwapIndex;//下一趟交换的搜索区间是上一趟的最后一次交换之前
            for(int j=0;j<temp;j++){
            if(array[j+1]<array[j]){
                ArrayUtil.swap(array,j,j+1);
                lastSwapIndex=j;
                swapped=true;
            }
            }

        }
    }

    public static void main(String[] args) {
        int []array= {4 ,18, 17, 5, 3 ,20 ,8 ,3 ,12 ,8 };
        RandomUtil.printArray(array);
        new BubbleSort().sort(array);
        RandomUtil.printArray(array);
    }
}

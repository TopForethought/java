package top.forethought.foroffer;

import top.forethought.common.utils.ArrayUtil;
import top.forethought.common.utils.RandomUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author  wangwei
 * @date     2019/3/16 16:33
 * @classDescription
 *   寻找前k大元素
 *   1. 使用快排分片思想
 *   2. 使用堆排序
 *
 *
 *  // 变式:输入是一只持续的,(流式输入)
 *     1. 如果使用排序:则是 N *  klgk
 *     2. 如果维护小顶堆(堆的大小是k):
 *        N*(1+lgk)=Nlgk
 *
 *
 */
public class FindKMax {


PriorityQueue priorityQueue=new PriorityQueue();

// 快速排序思想
    // 1,选定基准值 key
    // 2, 两头到中间扫描,left  停止在比key 小的元素
         //               right  停止在比key >=  的元素
    // 如果left <right
    // 这时三个元素之间的关系
    // 如果left<right
    //   input[left]<keyVal<=input[right]
    //     但是元素位置下标是:
    //       keyPos<left<right
            //    应该作出调整
            //   input[keyPos]=input[right]
            //   input[right]=input[left]
            //   keyPos=left;
            //   left++
    //
    int  partion(int []input,int left,int right){

          if(left>=right){
           return -1;
          }

           int  keyValue=input[left];
           while (left<right){

             // 从右往左找到比key  大的数
             while (left<right &&  input[right]<keyValue ) {
                 right--;
             }
                 if(left<right){ // 大数放到高位
                     input[left]=input[right];
             }
             // 从左往右找到比key小的数
             while ( left<right &&  input[left]>=keyValue) {
                 left++;
             }

               if(left<right){// 小数放到低位
                   input[right]=input[left];

               }


       }
       // 注意退出循环是:left==right
        // 如果input[left]>keyVal  ,则需要将keyVal放到left,left 元素放到keyPos
         input[left]=keyValue;
         return left;
    }
    void swap(int array[],int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }


    int [] findTopKMax(int array[],int k){

        int mid=partion(array,0,array.length-1);
        while(mid!=k){
            if(mid<k){
                mid=partion(array,mid+1,array.length-1);
            }else{
                mid=partion(array,0,mid-1);
            }
        }
        int []result=new int[k];
        System.arraycopy(array,0,result,0,k);
        return result;

    }

    void quickSort(int array[],int from,int to){

        int mid=partion(array,from,to);
        if (mid!=-1){
            quickSort(array,from,mid-1);
            quickSort(array,mid+1,to);
        }

    }

    public static void main(String[] args) {
        int []input={10, 17, 3, 4 , 16 ,5, 0, 7, 3 };
        RandomUtil.printArray(input);
        new FindKMax().quickSort(input,0,input.length-1);
        RandomUtil.printArray(input);
        int []topKResult=new FindKMax().findTopKMax(input,3);
        System.out.println("top k result");
        RandomUtil.printArray(topKResult);
    }

}

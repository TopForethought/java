package top.forethought.trees;


import top.forethought.common.utils.RandomUtil;

/**
 * @author  wangwei
 * @date     2019/3/9 8:52
 * @classDescription   完全二叉树:比满二叉树条件稍弱
 *                      一共k层的完全二叉树,只要求第k-1层是满的
 *                      并且第k层的节点集中在左侧,也就是右侧可能出现连续缺失情况
 *
 *                      大顶堆:根最大,每课子树只保证根大于左孩子,大于右孩子,但是不保证左右孩子之间的大小关系
 *
 *          给定序列,如何判断是否为堆呢?
 *          将序列按照层次遍历的形式,构建完全二叉树,观察即可
 *          比如:1  4 3  7 8 5
 *              1
 *             / \
 *            4   3
 *          /  \  / \
 *          7   8 5
 *    显然是小根堆
 *
 *    堆排序思想:
 *    1.以初始关键字序列,构建堆
 *    2.输出堆顶最小元素
 *    3,调整剩余元素,使其成为新堆
 *    4,重复2,3 直到n个元素全部输出,得到一个有序序列
 *
 *    如果输入为数组:
 *    当前为i  (i从1开始)
 *    则左孩子为 2*i
 *     右孩子:   2*i+1
 *
 *
 *     堆调整:将堆顶输出,最后孩子放置在堆顶,对剩余元素进行调整
 *            新堆顶与左右孩子比较,
 *           与较小孩子交换
 *           直到调整到叶子节点或者是 比左右孩子都小时,停止调整
 *      如何初始化序列建堆:从最后一个非叶子节点开始,向上调整,直到根节点
 *      //      最后一个非叶子节点是  n/2  向下取整
 *
 *
 *
 *      问题:为什么堆适合线性存储
 *      答:因为堆是完全二叉树结构,堆中元素的位置能根据父节点索引计算得到，
 *      所以不需要左右指针也可以找到子节点的位置
 *
 */
public class HeapSort {

    public  void init(int []array){
        if(null==array||0==array.length){
            return;
        }
        // 注意:计算  左孩子 2*i  ,这里表示编号是从1 开始
        //               数组从0开始,则需要是 2*i+1
        //               右孩子:2*i+2
        //  最后一个非叶子节点 索引:  n/2-1
        // 最后一个非叶子节点,可能只有左孩子
        for(int index=array.length/2-1;index>=0;index--){
            int parent=array[index];
            int lChild=array[2*index+1];

            // 只有左孩子
            if(2*index+2>array.length-1){
                if(parent>lChild){
                    array[index]=lChild;
                    array[2*index+1]=parent;
                }

            }
            // 左右孩子都有
            else {
                int rChild=array[2*index+2];
                // 处理  parent 不是三者之中最小的
                int minChildIndex=lChild<rChild? 2*index+1:2*index+2;
                if(parent>array[minChildIndex]){
                    array[index]=array[minChildIndex];
                    array[minChildIndex]=parent;
                }

            }
        }
    }

    //      waitAdjust  堆顶元素,此函数就是为了将其调整到特定位置,满足小顶堆
    //    索引在 minHeapEnd  区间是符合小顶堆的最后一个非叶子节点
    //
    public void adjustHeap(int []array,int minHeapEnd){
             int currentIndex=0;
             int waitAdjust=array[0];
             while (currentIndex<=minHeapEnd/2-1){
                 int leftChild=array[2*currentIndex+1];

                 // 只有左孩子
                 if(2*currentIndex+2>minHeapEnd){
                     if(leftChild>waitAdjust){
                         array[currentIndex]=leftChild;
                         currentIndex=2*currentIndex+1;// 走向左子树
                     }
                 }
                 // 左右孩子都有: 选取最小孩子交换
                 else{

                     int rChild=array[2*currentIndex+2];
                     int minChildIndex=leftChild<rChild? 2*currentIndex+1:2*currentIndex+2;
                     if(waitAdjust<=array[minChildIndex]){
                         break; // 结束调整
                     }else {
                         array[currentIndex]=array[minChildIndex];
                         currentIndex=minChildIndex;
                     }

                 }

             }
             array[currentIndex]=waitAdjust;
    }
     //将堆顶元素交换到tail
    public void pop(int[] array,int tail){
        int temp=array[0];
        array[0]=array[tail];
        array[tail]=temp;
    }
    public void heapSort(int array[]){
        if(null==array||0==array.length){
            return;
        }
        init(array);
        for(int i=0;i<array.length;i++){
            pop(array,array.length-1-i);
            adjustHeap(array,array.length-1-i-1);
        }
    }

    public static void main(String[] args) {
        int [] array= {2,5,3,12,8,17,10,20,19,13};
        RandomUtil.printArray(array);
        new HeapSort().heapSort(array);
        System.out.println("排序后:");
        RandomUtil.printArray(array);

    }
}

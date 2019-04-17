package top.forethought.jdkknowledge.queues;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *  使用堆来实现排序的,默认是小顶堆
 *  对象必须要是compareable 的
 */
public class ReadPriorityQueue {
    public static void main(String[] args) {

        PriorityQueue<Integer> queue=new PriorityQueue<>();

        Scanner in=new Scanner(System.in);
        while (in.hasNextInt()){
            Integer input=Integer.parseInt(in.nextLine());
            queue.add(input);
            System.out.println(queue.toString());
        }
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}

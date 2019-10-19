package top.forethought.foroffer;


import java.util.PriorityQueue;

public class TestPriorityQueue {
    public static void main(String[] args) {
        int [] array=new int[]{1,-1,2,-3,10};
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(-1);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }
}

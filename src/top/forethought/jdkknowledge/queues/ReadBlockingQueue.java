package top.forethought.jdkknowledge.queues;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

// 阻塞队列
// 入队，如果队列满，会阻塞直到有空间，然后入队
// 非阻塞队列则是：如果满，就丢掉未入队元素
// 线程池的实现都是阻塞队列实现
public class ReadBlockingQueue {
    @Test
    public void testBlockIngQueue() throws InterruptedException {
       LinkedBlockingQueue blockQueue=new LinkedBlockingQueue<>(3);
        blockQueue.add("java");
        blockQueue.add("c++");
        blockQueue.add("c#");
       // blockQueue.add("python");// add /offer 方法都可以实现insert 操作，不同的是add 失败，会抛出异常
                                 // offer 方法失败，会返回false
                                   // offer 可以设置超时时间插入，
        blockQueue.offer("python");// offer 操作是获取了的 putLock  （ReentrantLock）
        blockQueue.offer("pppp",10, TimeUnit.SECONDS);
        blockQueue.remove("c#"); // remove 操作会锁定 putLock,takeLock（写，读锁）
        blockQueue.poll();// 移除队头部元素， peek只是返回队头元素，不删除
        blockQueue.offer("python");
        System.out.println(blockQueue.toString());
    }
    @Test
    public void testNoBlockQueue(){

    }


}

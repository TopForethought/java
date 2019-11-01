package top.forethought.concurrency.threads.lock;

/**
 * lock  的优点：
 * 可中断退出
 * 可准确唤醒（多个condition）
 * 公平/非公平
 */

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A,B,C 三个线程，A 输出5次，B 输出10次，C输出15次  重复11轮（A->B->C）
 */

public class ReadLock {

    private int state = 0;//a1,b2,c3
    private ReentrantLock lock = new ReentrantLock();
    private Condition[] conditions=new AbstractQueuedSynchronizer.ConditionObject[3];// 每个线程对应一个condition

    {
        for (int i = 0; i < 3; i++) {
            conditions[i] = lock.newCondition();
        }
    }

    private void print(String content, int times, int threadNo) {
        try {
            lock.lock();
            while (state != threadNo) {
                conditions[threadNo].await();
            }
            for (int i = 0; i < times; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + content);
            }

        } catch (Exception e) {

        } finally {
            state+=1;
            state%=3;
            conditions[state].signal();// 唤醒下一个线程
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadLock readLock=new ReadLock();
        new Thread(()->{
            for(int i=0;i<11;i++)
            readLock.print("a",5,0);
        },"A").start();
        new Thread(()->{
            for(int i=0;i<11;i++)
            readLock.print("b",10,1);
        },"B").start();
        new Thread(()->{
            for(int i=0;i<11;i++)
            readLock.print("c",15,2);
        },"C").start();
    }

}

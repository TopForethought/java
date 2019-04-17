package top.forethought.concurrency.threads.lock;

import top.forethought.common.utils.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author  wangwei
 * @date     2019/3/30 11:02
 * @classDescription  ReentrantLock  可重入锁,同一个线程可以给对象重复加锁
 *                       ReentrantLock
 *        lock方法:Acquires the lock if it is not held by another thread and returns
 * immediately, setting the lock hold count to one
 *
 * 实现原理:AQS: AbstractQueuedSynchronizer
 *  AQS 同步队列是双向链表
 *  头结点是获取到锁的节点
 *  获取锁失败的线程,创建一个节点,使用cas 将其设置为新的尾节点
 *  释放锁:移除头结点,并且唤起后继节点,如果后继节点的前驱是被移除的那个头结点,那么这个节点如果获取同步状态成功,
 *         也就成为新的头结点
 *  unlock():释放锁,检查头结点的持有者是否为当前线程,如果不是,需要抛出异常
 *          状态是否为0,如果不是0(0是初始状态),将其移除,唤起直接最近符合条件后继节点(unparkSuccessor()如果有直接后继节点)
 *         unparkSuccessor(curr)
 *          为了避免后继节点为null,或者是被中断了,选择从尾向前寻找waitStatus 为<=0 的 并且
 *         是链表最靠前的(最接近 被移除的那个节点的节点)
 *          LockSupport.unpark(s.thread);// 这是为了给s.thread permit,让这个线程从blocked,变成unblocked
 *
 *  比较费时的操作时unlock ,这需要查找被移除节点的下一个可用(指waitStatus<=0的节点)后继
 *  waitStatus>0 的状态有:
 *  CANCELED:to indicate thread has cancelled
 *
 *  小于0的有:
*   SIGNAL  :to indicate successor's thread needs unparking  (需要获取permit)
    CONDITION :to indicate thread is waiting on condition
 *  PROPAGATE :to indicate the next acquireShared should
 *                unconditionally propagate
 *
 *
 *
 *
 *
 *    非公平锁的实现:nonfairTryAcquire: 只需要判断cas 设置状态成功,则表明当前线程获取到锁
 *    公平锁的实现:加锁时使用 fairTrAcquire: 非公平的基础上,加上了判断加入节点是否有前驱,如果有,则表明有线程更早的线程获取锁
 *                    因此需要等待前驱线程获取并释放锁之后才能继续获取锁
 *
 *
 *    一般锁的实现是采用了非公平锁的实现:
 *    公平锁保证了线程获取到锁是按照fifo的顺序获取
 *    非公平锁:只要获取了同步状态,则表明获取到锁,这样刚释放锁的线程再次获得同步状态的几率会很大
 *    这样会出现某些线程连续获取到锁,执行任务,这会减少上下文切换次数,提高吞吐量
 *
 */
public class ReadReentrantLock {
    private   ReentrantLock reentrantLock=new ReentrantLock();
    private int count=0;
    public void add(){
        while (!reentrantLock.tryLock()){
            System.out.println(Thread.currentThread().getName()+": 获取锁失败");
            try {
                Thread.currentThread().sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            count++;
            reentrantLock.unlock();


    }



    public static void main(String[] args) {

//        ReadReentrantLock read=new ReadReentrantLock();
//        List<Thread> threadList=new ArrayList<>();
//        for(int i=0;i<10;i++){
//         Thread thread=    new Thread(new CountRunable(read),i+"");
//         thread.start();
//         threadList.add(thread);
//        }
//        for(Thread t:threadList){
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(read.count);
        ReentrantLock lock=new ReentrantLock();

        for(int i=0;i<3;i++){
            lock.lock();
        }
        for(int i=0;i<3;i++){
            lock.unlock();
        }
        System.out.println("结束");
    }

   static class CountRunable implements  Runnable{
    ReadReentrantLock readReentrantLock;

        public CountRunable(ReadReentrantLock readReentrantLock) {
            this.readReentrantLock = readReentrantLock;
        }

        @Override
        public void run() {
            readReentrantLock.add();
        }
    }
}

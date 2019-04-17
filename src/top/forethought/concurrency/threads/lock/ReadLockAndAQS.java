package top.forethought.concurrency.threads.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

public class ReadLockAndAQS {


    /**java.util.concurrent.locks;
     * Lock:接口,提供了几个特别的方法,能实现synchronized关键字的功能以及synchronized没有的功能(比如,超时获取锁,获取中断锁)
     *    .lock() // 获取锁
     *    .lockInterruptibly()// 可中断获取,如果线程异常中断,取消获取锁
     *    .tryLock()// 尝试获取锁,成功返回true,失败返回false
     *    tryLock(long time, TimeUnit unit)// 超时获取锁,指定时间内未获取到锁,返回false
     *    .unLock()//释放锁
     *
     *
     *
     */
    /**  AbstractQueuedSynchronizer(抽象类,)
     *  Lock 接口只是定义了一些模板方法,但是具体的功能是交给AQS (AbstractQueuedSynchronizer)去实现的
     *
     *  AQS
     *  含有一个同步队列(双向链表),
     *  获取锁:
     *  获取锁失败的线程,被包装成一个Node 通过cas 放在尾节点
     *  释放锁: (头结点是获取同步状态成功的节点(线程),头结点释放锁时,会唤醒后继节点
     *       设置头结点是不需要cas 的,因为设置头结点这个操作是由获取同步状态成功的线程来操作的,而这个线程
     *       只有一个
     *
     *  主要方法:
     *  共享锁:
     *  获取:
     *   acquireShared(int arg);
     *
     *   释放:
     *   releaseShared(int arg);
     *  独占锁:
     *     获取:
     *   acquire(int arg);// 获取锁
     *   acquireInterruptibly(int arg)// 可中断获取
     *   tryAcquire()//尝试获取
     *   释放:
     *   release(int arg)
     *   tryRelease()
     *
     *   其中有个state,可以理解为信号量(资源数),如果是独占锁,这个取值只能为 0,1
     *                     0 表示已经有一个线程获取到锁
     *                     1 表示没有线程获取到锁
     *        如果state 取值范围为0,1,2,3,4
     *        表示这是一个共享锁,最多可同时有4个线程获取锁
     *        acquire();//state-1// 需要cas操作
     *        release();// state+1
     *
     *
     *  addWaiter(Node node)// 如果head!=null,使用cas将新节点设置到尾节点
     *
     *  否则调用 enq
     *  enq(Node);// 节点入队,如果head==null,使用cas 添加头头结点
     *             //否则  自旋,使用cas 将节点设置为尾节点
     *
     *
     *   acquireQueued(Node node,int arg);//节点一旦进入头部队列,自己开始自旋,检查自己的前驱节点是否是头结点
     *                如果只有前继节点是头结点,才能尝试获取同步状态
     *
     *         为什么是需要检查自己的前驱节点是否为头结点呢?
     *         由于非首节点线程前驱节点出队或者被中断而从等待状态返回,随后检查自己的前驱节点是否为头结点
     *         如果是,则尝试获取同步状态
     *         节点在循环检查的过程中基本不互相通信，只是简单的检查自己的前驱节点是否为头节点，这样使得节点的释放满足
     *         FIFO
     *
     */

}



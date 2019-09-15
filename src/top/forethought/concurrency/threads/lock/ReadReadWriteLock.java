package top.forethought.concurrency.threads.lock;



public class ReadReadWriteLock {
    /**
     *  读写锁:
     *  读锁: 线程共享
     *  写锁:排它锁
     *
     *  读写状态设计:
     *    可以在一个int 类型变量上维护多个状态,按位划分,高16位存读状态
     *                                                 低16位存写状态
     *  写锁的获取预释放:
     *  (写锁是可重入的排它锁)
         *   如果当前线程已经获取了写锁,则增加写状态
         *   如果当前线程在获取写锁时,发现读锁不为0,或者当前线程不是获取到写锁的线程,则当前线程进入等待状态
     *  读锁的获取与释放:
     *    (读锁是可重入(当前线程是获取了写锁的线程)的共享锁)
     *    获取读锁时,如果写状态为0,或者当前线程是获取了写锁的线程 ,那么就可以获取到读锁,增加读状态
     *    否则进入等待状态
     *
     *
     *  锁降级:写锁可降级为读锁
     *    指 当前持有写锁的线程,可以直接获取读锁,然后释放持有的写锁
     *
     *
     */

}
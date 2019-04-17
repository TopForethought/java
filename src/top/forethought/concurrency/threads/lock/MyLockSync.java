package top.forethought.concurrency.threads.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *  独占锁:所有功能的具体实现使用sync 对象代理去完成
 *  独占锁:只能由一个线程获取到并使用
 */
public class MyLockSync implements Lock {
   private static class Sync extends AbstractQueuedSynchronizer{
        // 是否处于独占状态
       @Override
       protected boolean isHeldExclusively(){
        return getState()==1;
       }

       // 当状态为0时获取锁
       @Override
       protected boolean tryAcquire(int arg) {
           if(compareAndSetState(0,1)){
               return true;
           }
           return false;
       }

       // 释放锁,将状态设置为0
       @Override
       protected boolean tryRelease(int arg) {
           if(getState()==0){
               throw new IllegalMonitorStateException();
           }
           setExclusiveOwnerThread(null);
           setState(0);
           return true;
       }
       // 返回一个condition ,每个condition都包含一个condition 队列
       Condition newCondition(){

           return new ConditionObject();
       }
   }

    private final Sync sync=new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {

        return  sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
     sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

     static int count=0;
     public static void main(String[] args) throws InterruptedException {
        final int clientCount=5000;
        final int threadNum=20;

        MyLockSync lockSync=new MyLockSync();
        CountDownLatch countDownLatch=new CountDownLatch(clientCount);
        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);
        for(int i=0;i<clientCount;i++){
            executorService.execute(()->{
                while (!lockSync.tryLock()) ;
                count++;
                lockSync.unlock();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("最终结果:"+count);
    }
}

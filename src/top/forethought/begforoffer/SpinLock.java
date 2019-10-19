package top.forethought.begforoffer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
     AtomicReference<Thread> lock=new AtomicReference<>();
    public void lock(){
         Thread t=Thread.currentThread();
         System.out.println(t.getName()+" 进入 lock");
         while (!lock.compareAndSet(null,t)){
             // 自旋直到获取到锁
         }

     }
    public void unLock(){
        Thread t=Thread.currentThread();
       lock.compareAndSet(t,null);// 只有持有锁的线程才能解锁
        System.out.println(t.getName()+" unlock");
    }

    public static void main(String[] args) {
        SpinLock spinLock=new SpinLock();
        new Thread(()->{
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                spinLock.unLock();
            }


        },"thread1").start();
        new Thread(()->{
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                spinLock.unLock();
            }
        },"thread2").start();
    }
}

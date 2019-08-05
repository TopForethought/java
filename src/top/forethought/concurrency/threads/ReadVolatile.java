package top.forethought.concurrency.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author : wangwei
 * description: volatile 这一关键字，保证了线程间的可见性
 *           volatile 变量自身具有的特性：
 *           1. 可见性：对一个volatile 变量的单个读/写 操作，总是能看到任意线程对这个变量的最后写入
 *           2. :原子性：对任意单个volatile 变量的读/写 具有原子性，但是对于 volatile ++ 这种复合操作不具有原子性
 * date:      2019/8/5
 */
public class ReadVolatile {




    // 多个线程对volatile  修饰的变量操作等价效果如下
    private volatile long value=0;
    public  synchronized  void set(long l){

         this.value=l;
     //   System.out.println(Thread.currentThread().getId()+" val:"+this.value);
     }

     public synchronized  long get(){
        return this.value;
     }
     public void  getAndIncrement(){
        long temp=get();
        temp=temp+1;
        set(temp);
     }
    public void  getAndIncrement2(){
        long temp=this.value;
        temp=temp+1;
        this.value=temp;
    }

    public static void main(String[] args) throws InterruptedException {
        int MAX_THRED =2000;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THRED);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        final ReadVolatile obj = new ReadVolatile();
        for (int i = 0; i < MAX_THRED; i++) {
            executorService.submit(() -> {
                //obj.getAndIncrement2();
                obj.getAndIncrement();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();


        System.out.println("result:" + obj.get());
    }


}

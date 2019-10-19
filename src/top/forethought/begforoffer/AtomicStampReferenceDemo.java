package top.forethought.begforoffer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;




public class AtomicStampReferenceDemo {
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) throws InterruptedException {
    new Thread(()->{
        atomicReference.compareAndSet(100,101);
        atomicReference.compareAndSet(101,100);
    },"t1").start();
    new Thread(()->{
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     System.out.println(   atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get());
    },"t2").start();
        // 以上是ABA 产生

    TimeUnit.SECONDS.sleep(2);
    new Thread(()->{
        int stamp=atomicStampedReference.getStamp();
        System.out.println(Thread.currentThread().getName()+"\t 第一次版本号"+stamp);
    },"t3").start();

        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次版本号"+stamp);
        },"t3").start();
    }



}

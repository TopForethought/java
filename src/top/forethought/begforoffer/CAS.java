package top.forethought.begforoffer;

import top.forethought.concurrency.threads.lock.ReadReadWriteLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * class: CAS 比较交换
 * author: wangwei
 * time : 2019/10/14
 */
public class CAS {
    // atomic 包则是如此
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger();
        atomicInteger.getAndIncrement();

        //  public final int getAndAddInt(Object o, long offset, int delta) {
        //        int v;
        //        do {
        //            v = getIntVolatile(o, offset);
        //        } while (!compareAndSwapInt(o, offset, v, v + delta));
        //        return v;
        //    }
    }
}

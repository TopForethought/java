package top.forethought.begforoffer;

import java.util.concurrent.atomic.AtomicInteger;

class Data{ // .java=>.class=>字节码
   volatile int number=0;
   AtomicInteger number2=new AtomicInteger();
    public void changeNumber(){
        number=1;
    }
    // 此时 是加上了volatile修饰,测试volatile的不保证原子性
    public  void addPlusPlus(){
        number++;
    }
    // atomic 去增加
    public void atomicAdd(){
        number2.getAndIncrement();
    }
}
/**
 * class: 测试 volatile 的可见性+非原子性
 * 如何保证原子性?
 * 使用atomic ,或者锁
 * author: wangwei  
 * time : 2019/10/13
 */
public class UnderstandVolatile {
    /**
     *  谈谈对volatile 的理解
     *   虚拟机提供的轻量级同步机制
     *   1.保证可见性
     *   2. 不保证原子性
     *   3.禁止指令重排
     */
    // 测试可见性
    public static void main(String[] args) {
      //  testVolatileVisible();
       // testNoAtomic();
        /**
         *  测试使用atomic包保证原子性
         */
        Data data=new Data();
        for(int i=0;i<20;i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    data.addPlusPlus();
                    data.atomicAdd();
                }
            },String.valueOf(i)).start();
        }
        // 等待 20 个线程全部计算完成,main线程取得最终结果值
        while (Thread.activeCount()>2){// main+gc
            Thread.yield();
        }
        System.out.println("result:"+data.number+" atomic:"+data.number2.get());// 值为20000
    }

    /**
     * 2. 验证 volatile 不保证原子性(也就是操作不能被打断)
     * 20 个线程执行 1000 次 ++ 操作
     */
    private static void testNoAtomic() {

        Data data=new Data();
        for(int i=0;i<20;i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    data.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        // 等待 20 个线程全部计算完成,main线程取得最终结果值
        while (Thread.activeCount()>2){// main+gc
            Thread.yield();
        }
        System.out.println("result:"+data.number);// 值 小于 20000
    }

    /**
     *  1 .验证volatile 保证可见性
     *   不加volatile ,没有可见性
     *   加上volatile 可以保证可见性
     */
// 1. 测试 volatile 的可见性
    private static void testVolatileVisible() {
        Data data=new Data();
        // 1. data 不加volatile   //
        //      结果:CHANGE come in
        //           CHANGE updated value:1
        // 2. data 加上volatile
        // 结果: CHANGE come in
        //       CHANGE updated value:1
        //       main: finished
        // 第一个线程去改数据,用3s 完成数据更改
        new Thread(()->{
           System.out.println(Thread.currentThread().getName()+" come in");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.changeNumber();
            System.out.println(Thread.currentThread().getName()+" updated value:"+data.number);
        },"CHANGE").start();
        // 第二个线程 main ,等待线程 1 完成数据更改
        while (data.number==0){

        }
        System.out.println("main: finished");
    }

}

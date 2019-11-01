package top.forethought.concurrency.threads;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * 测试volatile  的可见性
 */
public class ReadVolatile2 {
    private volatile boolean isEnd;
    private boolean isEnd2;

    public void stop() {
        isEnd = true;
    }
    public void stop2(){
        isEnd2=true;
    }

    public static void main(String[] args) throws InterruptedException {
        ReadVolatile2 readVolatile2 = new ReadVolatile2();
       // 线程1 通过判断 isEnd作为条件循环输出时间
        // 线程2 通过判断isEnd2 作为条件循环输时间
        // 线程3（main） 去将isEnd,isEnd2 置为true, 观察线程1，线程2 是否能够退出

        new Thread(()->{
            while (!readVolatile2.isEnd){
                //System.out.println(Thread.currentThread().getName()+" :"+new Timestamp(System.currentTimeMillis()));
            }
            System.out.println(Thread.currentThread().getName()+" end");
        },"thread1").start();
        new Thread(()->{
            while (!readVolatile2.isEnd2){
             //(如果这里不执行语句，thread2就不能停止)  System.out.println(Thread.currentThread().getName()+" :"+new Timestamp(System.currentTimeMillis()));
            }
            System.out.println(Thread.currentThread().getName()+" end");
        },"thread2").start();
        TimeUnit.SECONDS.sleep(2);//先让线程1 充分运行
        readVolatile2.stop();
        readVolatile2.stop2();
    }
}

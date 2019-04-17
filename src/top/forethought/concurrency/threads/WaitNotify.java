package top.forethought.concurrency.threads;

import top.forethought.common.utils.SleepUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2019/3/10 9:15
 * @classDescription 场景:一个线程修改了一个对象的值,而另一个线程感觉到了变化,然后进行相应的操作
 * 整个过程开始于一个线程,作用于另一个线程`,前者是生产者,后者是消费者
 * <p>
 * 这种生产者消费者模式隔离了"做什么" 和"怎么做"
 * java 中的实现方式
 * while(value!=desire){
 * Thread.sleep(1000);
 * }
 * doSomething();
 * <p>
 * 上述代码:在条件不满足时,sleep一段时间,避免过快的"无效尝试"
 * <p>
 * 存在的问题:
 * 1.难以确保及时性:睡眠时基本不占用处理器资源,但是如果睡眠过久,难以及时发现条件已经变化
 * 2.难以降低开销:如果降低睡眠时间,消费者能及时发现条件变化,但是,却可能需要浪费更多的资源
 * <p>
 * 两点矛盾,但是java内置了等待/通知机制,可以解决这个问题
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                // 当条件不满足时,继续wait,但是会释放锁lock
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true,wait @ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //条件满足时,完成工作
                System.out.println(Thread.currentThread() + " flag is false,running@ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            }

        }
    }


    static class Notify implements Runnable {

        @Override
        public void run() {
            //加锁,拥有lock 的monitor
            synchronized (lock) {
                // 获取lock锁,然后进行通知(不会释放锁)
                // 直到当前线程释放lock锁之后,WaitThread 才能在wait方法上返回
                System.out.println(Thread.currentThread() + " hold lock ,notify @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtil.second(5);

            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again ,sleep @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtil.second(5);
            }
        }
    }
}


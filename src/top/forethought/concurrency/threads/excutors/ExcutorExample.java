package top.forethought.concurrency.threads.excutors;

import top.forethought.concurrency.threads.ThreadRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// 两级调度模型
// 任务->executors->线程池->os 内核->cpu
// 为什么需要线程池？
// 线程的生命状态;新建-》就绪(等待cpu调度）-》运行-》结束
// 线程的核心部分是运行，但是状态转换需要时间
// 如果使用线程池，线程池则是创建一定数量的线程来执行运行部分，少了其他状态，使得run 更快执行


//Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
//
//主要有三种 Executor：
//
//    CachedThreadPool：一个任务创建一个线程；
//    FixedThreadPool：所有任务只能使用固定大小的线程；
//    SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool
// 底层构造方法都是：
//ThreadPoolExecutor(nThreads, nThreads,
//        0L, TimeUnit.MILLISECONDS,
//        new LinkedBlockingQueue<Runnable>());
public class ExcutorExample {

    public static void main(String[] args) {
//      ExecutorService executorService=   Executors.newCachedThreadPool();
//        ExecutorService executorService= Executors.newSingleThreadExecutor();
        ExecutorService executorService= Executors.newFixedThreadPool(2);

        for(int i=0;i<50;i++){
            executorService.execute(new ThreadRunnable());
        }
        System.out.println("before shut down ");
//        注意调用shutdown 方法,并不会立刻将线程中断
        executorService.shutdown();
        System.out.println("after shut down ");

       System.out.println("唤醒 waiting thread");
    }
}

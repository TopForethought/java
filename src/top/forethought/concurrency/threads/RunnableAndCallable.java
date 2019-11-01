package top.forethought.concurrency.threads;


import sun.awt.windows.ThemeReader;

import java.util.concurrent.*;

public class RunnableAndCallable {
    public static void main(String[] args) throws Exception {
       // new CallableDemo().call();//同步方法
        FutureTask futureTask=new FutureTask(new CallableDemo());
        futureTask.run();
//        while (!futureTask.isDone()){
//            System.out.println(futureTask.get());//会阻塞
//        }
//
//        new Thread(futureTask,"thread").start();
        System.out.println(futureTask.get());//会阻塞，耗时，尽量放在靠后
        System.out.println(Thread.currentThread().getName()); // fork join  操作：新起线程去执行 futureTask 耗时操作，最终取回线程执行结果
        ThreadPoolExecutor.AbortPolicy;
        ThreadPoolExecutor.CallerRunsPolicy;
        ThreadPoolExecutor.DiscardOldestPolicy;
        ThreadPoolExecutor.DiscardPolicy
    }
}


class RunnableDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("this is runnable");
    }
}

class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        System.out.println("this is callable ,do something "+Thread.currentThread().getName());


        return 100;
    }
}
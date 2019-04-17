package top.forethought.concurrency.threads.httpserver;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchWorkQueueException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程池默认数量

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBERS = 1;

    // 这是工作列表
    private final LinkedList<Job> jobs = new LinkedList<>();
    // 工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者线程数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initWorkers(workerNum);
    }


    @Override
    public void execute(Job job) {
        // 添加一个工作(任务)然后进行通知工作线程
        if (job != null) {
            synchronized (jobs) {
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    // 停止所有worker
    @Override
    public void shutDown() {
        for (Worker w : workers) {
            w.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            //限制新增的worker数量不能超过最大值
            if (workerNum + num > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - workerNum;
            }
            initWorkers(num);
            this.workerNum += num;
        }
    }

    //  移除工作者
    @Override
    public void removeWorker(int num) {

       synchronized (jobs){
           if (num > workerNum) {
               throw new IllegalArgumentException("beyond workerNum");
           }
           // 按照给定的数量停止工作者
           int count=0;
           while (count<num){
               Worker worker=workers.get(count);
               if(workers.remove(worker)){
                   worker.shutdown();
                   count++;
               }
           }
           this.workerNum-=count;
       }

    }

    @Override
    public int gtJobSize() {
        return jobs.size();
    }

    // 初始化线程工作者
    private void initWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "Thread-worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 工作者,负责消费任务
    class Worker implements Runnable {
        // 是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    // 如果工作列表是空的,就等待
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                            // 感知到外部对线程的中断操作,返回
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();// 清除中断标记,同时退出线程
                            return;
                        }
                    }
                    // 取出一个job
                    job = jobs.removeFirst();

                }
                if (job != null) {
                    job.run();
                }


            }
        }

        public void shutdown() {
            running = false;
        }
    }

    //
}

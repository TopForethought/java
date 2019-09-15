package top.forethought.concurrency.threads.lock;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author  wangwei
 * @date     2019/3/30 16:23
 * @classDescription   semphore:信号量,线程拿到许可才能继续工作
 * 场景:连接池的使用,通过信号量的大小来限制最大访问数据库的并发连接数
 *
 */
public class SemphoreExample {

       // CyclicBarrier
        private static final int maxThreadCount=3;
        private static final int clientCount=40;


        public static void main(String[] args) throws InterruptedException {

            ExecutorService executorService= Executors.newCachedThreadPool();
            Semaphore semaphore=new Semaphore(maxThreadCount);
            for(int i=0;i<clientCount;i++){
                final int threadNo=i;
                executorService.execute(()->{
                    try {
                        semaphore.acquire(1);// 获取许可,可以是多个,默认是1个
                        test(threadNo);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release(1);//释放许可,可以是多个,默认是1个
                    }

                });
            }
            System.out.println("finished");

            executorService.shutdown();
        }




        public static   void test(int threadNo){
            try {
                SECONDS.sleep(1);
                System.out.println("threadNo:"+threadNo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }



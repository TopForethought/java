package top.forethought.concurrency.threads.lock;








import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author  wangwei
 * @date     2019/3/30 15:34
 * @classDescription   场景:1.所有子线程执行完,才继续主线程
 *     主要操作:countDown :计数器减1
 *     .await() 计数器减为0时,调用此方法唤起所有线程,执行完毕继续主线程
 *
 */


public class CountDownLatchExample1 {
    private static final int maxThreadCount=20;
//private static final int clientCount=5000;


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch  countDownLatch=new CountDownLatch( maxThreadCount);
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CountDownLatchExample2 countDownLatchExample=new CountDownLatchExample2();
        for(int i=0;i<maxThreadCount;i++){
            final int threadNo=i;
            executorService.execute(()->{

                countDownLatchExample.test(threadNo);
                countDownLatch.countDown();
            });
        }
        // 限时唤醒
        countDownLatch.await();// countDown 计数器减为0时,这里会唤起所有阻塞的线程
        System.out.println("finished");// finished 一定是在最后输出的

        executorService.shutdown();
    }




    public  void test(int threadNo){
        try {
            MILLISECONDS.sleep(2);
            System.out.println("threadNo:"+threadNo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

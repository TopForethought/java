package top.forethought.concurrency.threads.lock;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author  wangwei
 * @date     2019/3/30 20:14
 * @classDescription
 * 共享式锁:允许多个线程获取锁
 * 思路:使用aqs 提供的 acquireShared()和shared 相关方法
 *    定义资源数:同一时刻最多支持2两个线程访问,表明同步资源数为2
 *    初始化status=2, 一旦一个线程获取到锁,资源数-1
 *     status=0 表示已经有两个线程获取到锁,
 *     status=2 表示没有线程获取到锁
 *     释放锁 时status+1
 *     status 合法范围是 0,1,2
 *
 *     以下的测试代码是:
 *     开启20个线程,每个线程run 是一个死循环
 *     {获取锁
 *     输出线程编号
 *     释放锁}
 *     最终看到的效果是:线程名称成对出现,也就是同一时间只能由两个线程获取到锁
 *
 *
 */
public class TwinsLock  implements Lock{

    private Sync sync=new Sync(2);
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
       sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
     return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
      sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
    private  static class Sync extends AbstractQueuedSynchronizer{
      public   Sync(int count){
            if(count<=0){
                throw new IllegalArgumentException("count  必须是大于0");
            }
            setState(count);
        }
        //尝试获取共享锁
        // 返回值>=0时,表示当前线程获取到同步状态
        public int tryAcquireShared(int reduceCount){
          for(;;){
             int current=getState();
             //计算获取到锁之后的同步状态
             int newCount=current-reduceCount;
             if(newCount<0||compareAndSetState(current,newCount)){
                 return newCount;
             }
          }
        }
        public boolean tryReleaseShared(int returnCount){
          for(;;){
              int current=getState();
              int newCount=current+returnCount;
              if(compareAndSetState(current,newCount)){
                  return true;
              }
          }
        }

    }

    public static void main(String[] args) {
        final Lock twinsLock=new TwinsLock();
        final int threadCount=20;
        ExecutorService executorService= Executors.newFixedThreadPool(threadCount);


        for(int i=0;i<threadCount;i++){
            final int thNo=i;
            executorService.execute(()->{
                while (true){
                    twinsLock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("thread:"+thNo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        twinsLock.unlock();
                    }
                }
            });
        }
        // 每隔1秒换行
        for(;;){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}

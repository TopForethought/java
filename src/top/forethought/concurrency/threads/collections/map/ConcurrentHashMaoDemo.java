package top.forethought.concurrency.threads.collections.map;


import java.util.Map;
import java.util.concurrent.*;
/**
 * @author  wangwei
 * @date     2019/3/30 16:48
 * @classDescription
 *  ConcurrentHashMap: jdk1.7 通过分片加锁的方式,保证线程安全,改变了以往的全表加锁的方式
 *
 *   jdk1.8 采用锁分离思想:插入节点时,如果是新节点,就不加锁,创建一个节点插入就好
 *                        如果是存在相同hash的节点(链表头结点),则将头结点加锁(synchronized),加锁之前的操作都是
 *                        无锁化的cas和volatile
 *   数据是无序的:
 */
public class ConcurrentHashMaoDemo {

    public static final int maxThreadCount = 20;
    public static final int clientNum = 5000;
    private static final Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadCount);
        CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        Semaphore semaphore = new Semaphore(maxThreadCount);
        for (int i = 0; i < clientNum; i++) {
            final int data = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(data);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }

            });

        }
        countDownLatch.await();

        executorService.shutdown();
        System.out.println("size:" + map.size());

    }

    public static void add(int data) {
        map.put(data, data);
    }
}

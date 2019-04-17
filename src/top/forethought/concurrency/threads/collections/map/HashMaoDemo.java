package top.forethought.concurrency.threads.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
/**
 * @author  wangwei
 * @date     2019/3/30 19:10
 * @classDescription
 * HashMap 线程问题主要是出现在扩容后rehash 操作,出现循环链表,导致死循环
 *
 */
public class HashMaoDemo {
    public static final int maxThreadCount = 20;
    public static final int clientNum = 5000;
    private static final Map<Integer, Integer> map = new HashMap<>();

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

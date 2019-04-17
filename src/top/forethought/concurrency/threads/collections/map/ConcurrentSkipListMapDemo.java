package top.forethought.concurrency.threads.collections.map;

import java.util.Map;
import java.util.concurrent.*;
/**
 * @author  wangwei
 * @date     2019/3/30 16:53
 * @classDescription   数据是有序的,并发能力不受线程规模影响
 *
 */
public class ConcurrentSkipListMapDemo {
    public static final int maxThreadCount = 20;
    public static final int clientNum = 5000;
    private static final Map<Integer, Integer> map = new ConcurrentSkipListMap<>();

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

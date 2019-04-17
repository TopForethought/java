package top.forethought.concurrency.threads.collections.set;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class CopyOnWriteArraySetDemo {
    public static final int maxThreadCount = 20;
    public static final int clientNum = 5000;
    private static final Set<Integer> set = new CopyOnWriteArraySet<>();

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
        System.out.println("size:" + set.size());

    }

    public static void add(int data) {
        set.add(data);
    }
}

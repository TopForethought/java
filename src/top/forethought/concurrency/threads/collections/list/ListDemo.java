package top.forethought.concurrency.threads.collections.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ListDemo {
    public static final int maxThreadCount = 20;
    public static final int clientNum = 5000;
    private static final List<Integer> list = new ArrayList<>();

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
        System.out.println("size:" + list.size());

    }

    public static void add(int data) {
        list.add(data);
    }
}

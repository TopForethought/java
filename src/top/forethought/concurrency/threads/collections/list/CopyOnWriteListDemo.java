package top.forethought.concurrency.threads.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author  wangwei
 * @date     2019/3/30 16:56
 * @classDescription
 * CopyOnWriteList: 读写分离
 *      读是在原数据区读,不加锁
 *      写:是通过ReentrentLock 加锁,将原数据拷贝一份,在新的内存上写,写完之后,指向新的内存
 *       注意:适用于读多写少的场景
 *            适用于数据量较少(大数据量复制会消耗性能)
 *            读到的数据可能不是最新的数据
 *
 *      public boolean add(E e) {
final ReentrantLock lock = this.lock;
lock.lock();
try {
Object[] elements = getArray();
int len = elements.length;
Object[] newElements = Arrays.copyOf(elements, len + 1);
newElements[len] = e;
setArray(newElements);
return true;
} finally {
lock.unlock();
}
}
 *
 */
public class CopyOnWriteListDemo {
    public static final int maxThreadCount = 20;
    public static final int clientNum = 5000;
    private static final List<Integer> list = new CopyOnWriteArrayList<>();

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

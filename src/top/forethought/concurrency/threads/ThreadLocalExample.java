package top.forethought.concurrency.threads;

public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal.set(1);
            // public void set(T value) {
            //        Thread t = Thread.currentThread();
            //        ThreadLocalMap map = getMap(t);
            //        if (map != null)
            //            map.set(this, value);
            //        else
            //            createMap(t, value);
            //    }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
            threadLocal.remove();
        });
        Thread thread2 = new Thread(() -> {

            threadLocal.set(2);

            threadLocal.remove();
        });
        thread1.start();
        thread2.start();
    }
}

package top.forethought.concurrency.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> futureList=new ArrayList<>();
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            Callable callable=new Callable<String>(){

                @Override
                public String  call() throws Exception {
                    return Thread.currentThread().getName();
                }
            };
             Future f=  executorService.submit(callable);
            futureList.add(f);
        }
        for (Future f:futureList) {
            System.out.println(f.get().toString());
        }
        executorService.shutdown();
    }
}

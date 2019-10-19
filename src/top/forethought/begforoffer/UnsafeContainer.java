package top.forethought.begforoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
//ArrayList,HashSet 线程不安全

public class UnsafeContainer {
    public static void main(String[] args) {
      //  List<String> list=new ArrayList<>(); 线程不安全
//        List<String> list= Collections.synchronizedList(new ArrayList<>());// 线程安全
//        CopyOnWriteArraySet
        List<String> list= new CopyOnWriteArrayList<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(1,8));
                System.out.println(String.valueOf(list));
                //java.util.ConcurrentModificationException
            }).start();

        }
    }
}

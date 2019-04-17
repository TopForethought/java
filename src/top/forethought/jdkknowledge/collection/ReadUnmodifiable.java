package top.forethought.jdkknowledge.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *   unmodifiable 下的容器是禁止对容器的读写操作,但不是表示其中元素指向的对象不可修改
 */
public class ReadUnmodifiable {
   static class SimpleObject{
        String name;
        public SimpleObject(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) {
        List<String> objectList=new ArrayList<>();
        objectList.add("hello");
        objectList.add("world");
     List<String> unmodifiableList=    Collections.unmodifiableList( objectList);
      for(String str:unmodifiableList){
         str+="modify";
     }
    }
}

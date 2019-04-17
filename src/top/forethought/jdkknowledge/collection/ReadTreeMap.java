package top.forethought.jdkknowledge.collection;

import java.util.Iterator;
import java.util.TreeMap;
/**
 * @author  wangwei
 * @date     2019/3/20 12:25
 * @classDescription
 * 1. iterator 不支持在遍历过程中删除
 * 2.
 *
 *
 *
 */
public class ReadTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap=new TreeMap<>();
        treeMap.put(1,"张三");
        treeMap.put(2,"李四");

    Iterator<Integer> it=  treeMap.keySet().iterator();
      while (it.hasNext()){
          Integer key=it.next();
         System.out.println(key+":"+treeMap.get(key));
      }
    }
}

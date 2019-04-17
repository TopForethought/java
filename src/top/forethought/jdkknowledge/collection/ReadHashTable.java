package top.forethought.jdkknowledge.collection;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
/**
 * @author  wangwei
 * @date     2019/3/23 19:23
 * @classDescription  hashTable 是同步的(synchronized)
 *            实现了Map 接口
 *
 */
public class ReadHashTable {
    public static void main(String[] args) {
      Hashtable<String,String> stringHashtable=new Hashtable<>();
      stringHashtable.put("key1","val1");
        stringHashtable.put("key2","val2");
       Set<Map.Entry<String,String>> entrySet=  stringHashtable.entrySet();
        System.out.println("helloworld");
    }
}

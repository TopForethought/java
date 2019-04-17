package top.forethought.jdkknowledge.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  wangwei
 * @date     2019/3/30 9:28
 * @classDescription 测试堆溢出:对象都是在堆内存上创建,如果对象所占内存太大,会导致堆溢出
 *    //java.lang.OutOfMemoryError: Java heap space
 *
 */
public class TestOOM {
    public static void main(String[] args) {

        List<Object> objects=new ArrayList<>();
        while (true){
            objects.add(new Object());
        }
    }
}

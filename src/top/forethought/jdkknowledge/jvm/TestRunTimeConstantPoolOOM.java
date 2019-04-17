package top.forethought.jdkknowledge.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  wangwei
 * @date     2019/3/30 10:09
 * @classDescription  运行时常量区溢出
 *     -XX:PermSize10M  (永久代参数,在jdk8中永久代被废弃,使用元空间(使用的是堆外内存)
 *     -XX:PermMaxSize10M
 *
 *     运行时常量区是在永久代(也就是方法区)
 *
 */
public class TestRunTimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}

package top.forethought.jdkknowledge;

import java.util.concurrent.locks.Lock;
// -128---127  数据存放在常量池
// 不在此范围或者new 创建数据存放在堆
public class SpecialNumber {
    public static void main(String[] args) {
        Integer a=100;
        Integer b=100;
        System.out.println(a==b);
        Integer c=1000;
        Integer d=1000;
        System.out.println(c==d);
        Integer e=new Integer(100);
        // 注意Integer  -128---127 范围内可以通过== 比较
        // 其他范围需要通过 equals  比较
        Integer f=new Integer(100);
        System.out.println(e==f);
    }
}

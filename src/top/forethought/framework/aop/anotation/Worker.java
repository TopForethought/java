package top.forethought.framework.aop.anotation;

import top.forethought.framework.aop.BaseInterface;

public class Worker implements BaseInterface {
    @Override
    public void study() {
        System.out.println("工作了,也要抽出时间学习啊");
    }
}

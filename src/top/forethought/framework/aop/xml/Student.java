package top.forethought.framework.aop.xml;

import top.forethought.framework.aop.BaseInterface;

public class Student implements BaseInterface {

    @Override
    public void study() {
        System.out.println("发现群里有抖音分享,打开抖音,逐渐忘记时间");
    }
}

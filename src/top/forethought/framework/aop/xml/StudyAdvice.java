package top.forethought.framework.aop.xml;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class StudyAdvice implements MethodBeforeAdvice,AfterReturningAdvice {


    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
     System.out.println("打开手机,准备学习");
    }
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("时间不早了 ,没有学习,明天再学吧");
    }

}

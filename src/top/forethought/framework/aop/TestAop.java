package top.forethought.framework.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Spring支持五种类型的通知：
 Before(前)  org.apringframework.aop.MethodBeforeAdvice
 After-returning(返回后) org.springframework.aop.AfterReturningAdvice
 After-throwing(抛出后) org.springframework.aop.ThrowsAdvice
 Arround(周围) org.aopaliance.intercept.MethodInterceptor
 Introduction(引入) org.springframework.aop.IntroductionInterceptor

 */
public class TestAop {



    @Test
    public void testAop01(){
        String xmlPath="top/forethought/framework/configs/aop-config.xml";
        ApplicationContext context= new ClassPathXmlApplicationContext(xmlPath);
        BaseInterface baseInterface= (BaseInterface) context.getBean("studentProxy");
        baseInterface.study();
    }
}

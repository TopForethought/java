package top.forethought.framework.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {

    @Test
    public void noneIoc(){
        BookService bookService=new BookServiceImpl();
        bookService.addBook();
    }
    @Test
    public void byIoc(){
        String xmlPath="top/forethought/framework/configs/ioc-config.xml";
        ApplicationContext context= new ClassPathXmlApplicationContext(xmlPath);
         BookService bookService= (BookService) context.getBean("bookServiceId");
        bookService.addBook();
        for(int i=0;i<10;i++){
            bookService= (BookService) context.getBean("bookServiceId");
            System.out.println(bookService.hashCode());
        }
    }
}

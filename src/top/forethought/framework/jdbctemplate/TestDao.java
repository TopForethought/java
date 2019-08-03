package top.forethought.framework.jdbctemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;


public class TestDao {

    @Test
    public void testInsert() {

        User user = new User();
        user.setName("张三");
        user.setSex("男");
        user.setDescription("helloworld");
        String xmlPath = "top/forethought/framework/configs/beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        UserDao dao = (UserDao) context.getBean("userDaoId");
        dao.insert(user);
    }

    @Test
    public void testAddBatch() {


        String xmlPath = "top/forethought/framework/configs/beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        UserDao dao = (UserDao) context.getBean("userDaoId");

        List<User> userList = new ArrayList<>();
        User user;
        for (int i = 0; i < 1000000; i++) {
            user = new User();
            user.setName("张" + Math.random() * 100);
            user.setSex("男");
            user.setDescription("HEsdsdaO" + Math.random() * 100);
            userList.add(user);
        }
        long start = System.currentTimeMillis();
        dao.addBatch(userList);
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        // batchSize 100 耗时:187405
        //     1000             163520
        // 10000      182308
    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println('Z' - Math.random() * 10);
        }
    }

    @Test
    public void testNoneSpring() {
        try {
            //1,加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2,配置数据源
            ComboPooledDataSource dataSource=new ComboPooledDataSource();// 看见new 想到ioc
            dataSource.setUser("noneroot");
            dataSource.setPassword("18142513872");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test_index?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT");
            //3,配置模板
            JdbcTemplate jdbcTemplate=new JdbcTemplate();
            jdbcTemplate.setDataSource( dataSource);// 看见set ,想到DI
            // 4,注入模板
            UserDao userDao=new UserDao();
            userDao.setJdbcTemplate(jdbcTemplate);
            //5,使用dao
            userDao.insert(new User("我的名字","男","描述"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testWithSpringIoc() {

        String xmlPath = "top/forethought/framework/configs/beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        UserDao dao = (UserDao) context.getBean("userDaoId");
        dao.insert(new User("我的名字ioc","男","描述ioc"));
    }

}


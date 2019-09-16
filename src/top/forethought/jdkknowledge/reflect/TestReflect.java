package top.forethought.jdkknowledge.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// 给定一个map
// 给定一个类
// 将map 中的 val 设置到 该类的一个对象的属性中
class User{
    private  String name;// final 修饰则不能反射设置参数
    private String password;

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
public class TestReflect {


    public Object mapToObject(Map<String,String> map, Class cla) throws IllegalAccessException, InstantiationException {
      Object instance=  cla.newInstance();
     Field[] fields= cla.getDeclaredFields();
     for(Field f:fields){

         f.setAccessible(true);
         f.set(instance,map.get(f.getName()));
     }
     return instance;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Map<String,String> map=new HashMap<>();
        map.put("name","zhangsan");
        map.put("password","密码");
        User user= (User) new TestReflect().mapToObject(map,User.class);
        System.out.println(user);
    }
}

package top.forethought.begforoffer;

import java.util.concurrent.atomic.AtomicReference;
class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
       User user1=new User("zhangsan",10);
       User user2=new User("lisi",20);
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(user1);
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get());
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get());
    }
}

package top.forethought.jdkknowledge;
public class ClassA {
    static int count = 0;
    static {
        count++;
        System.out.println("A");
    }
    public ClassA() {
        System.out.println("B");
    }
}



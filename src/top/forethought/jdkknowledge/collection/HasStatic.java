package top.forethought.jdkknowledge.collection;

public class HasStatic {
    private static int x=100;

    public static void main(String[] args) {
        HasStatic hasStatic1=new HasStatic();
        hasStatic1.x++;
        System.out.println(x);
    }
}

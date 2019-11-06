package top.forethought.javagrammer.generics;

/**
 *
 *  通配符：<?>
 *
 *
 */
public class GenernicDemo2<T> {
  T val;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public static void main(String[] args) {
        //GenernicDemo2<?> demo=new GenernicDemo2();// <?> 可以接受所有指定泛型的对象，但是不能设置具体值
        //demo.setVal("1234");//不能设置参数
        GenernicDemo2<String> demo=new GenernicDemo2();
        demo.setVal("李太白");
        printString(demo);

    }
    public static void printString(GenernicDemo2<?> d){
        System.out.println(d.getVal());
    }
}

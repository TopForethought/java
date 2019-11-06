package top.forethought.javagrammer.generics;

/**
 *  泛型类定义
 *  访问修饰符 class  类名 < 泛型类型1，...>{
 *
 *  }
 */
public class GenernicDemo<K ,V > {
    K key;
    V val;
    public void setKey(K k){

    }
    public K getKey(){
        return key;
    }

    public static void main(String[] args) {
        // 对象定义
        // 类名<具体类型，...> 对象名=new 类名<具体类型，...>();
        GenernicDemo demo=new GenernicDemo<Integer,String>();
        // 注意如果在声明对象时，没有指明具体类型，会默认为是object 类型，会有警告
        GenernicDemo demo2=new GenernicDemo();
        demo2.setKey("123");
        demo2.getKey();
    }
}

package top.forethought.javagrammer.generics;

/**
 * 泛型方法， 不需要类是泛型类
 */
interface  IC{

}
interface IC1 extends IC{

}
interface ID{

}
interface ID1 extends ID{

}
public class GenerincDemo4 {
// 提供一个方法能够将 IC 类型的转换为 ID 类型返回
//public static  <T extends IC> ID convert(T t){
//
//
//    }
public static <T> void stringfy(T t){

}
}

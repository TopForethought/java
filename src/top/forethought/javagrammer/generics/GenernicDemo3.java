package top.forethought.javagrammer.generics;



/**
 *
 *  受限制泛型
 *
 *
 */

public class GenernicDemo3<T> {
    T val;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public static void main(String[] args) {

    }
    //// T 只能是A 的子类, 如果 A 是接口，那么T 就是A 的子接口
    //   // 类似的有 限制上限   T super A1   T 只能是A1 的父类或者父接口
    public static void printString(GenernicDemo3<? extends A> param){ //泛型只能是A 类型或者是A 的子类型，指定上限为A，在类定义时可以使用
                                                                      // 如果限制泛型下限，可以使用？ super A，类定义时不可以使用
        System.out.println(param.getVal());
    }



}
//  // 类似的有泛型接口（类比泛型类）
interface  IA<T>{

}
class AImpl<T> implements IA<T>{

}
// 类似的有泛型方法

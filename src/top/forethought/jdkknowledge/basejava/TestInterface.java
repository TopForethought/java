package top.forethought.jdkknowledge.basejava;

/**
 * @author  wangwei
 * @date     2019/3/16 16:27
 * @classDescription
 * 接口可以多继承,但是类不可以
 * 接口中属性:public  static final
 * 接口中方法:public abstract
 *
 */
public interface TestInterface extends Interface1,Interface2 {
    String name="helloworld";
}

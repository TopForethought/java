package top.forethought.jdkknowledge.jvm;

import java.io.InputStream;

public class MyClassLoader extends  ClassLoader{

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName=name.substring(name.lastIndexOf('.')+1)+".class";
        // 双亲委派模式,要求先到父类加载器取尝试加载,如果不能加载,在由当前加载器加载
        // 这里自定义的类加载器,显示是先尝试使用当前类加载器加载,加载不了才到父类加载器去加载
        InputStream inputStream=getClass().getResourceAsStream(fileName);
        try{
            if(null==inputStream){
                return super.loadClass(name);
            }
            byte[]bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){
            e.printStackTrace();
            throw  new ClassNotFoundException(name);
        }

    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
     MyClassLoader classLoader=new MyClassLoader();
        Object obj=classLoader.loadClass("top.forethought.jdkknowledge.jvm.MyClassLoader").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof top.forethought.jdkknowledge.jvm.MyClassLoader);//false


    }
    /**
     * MyClassLoader 可以加载与自己在同一个路径下的Class 文件,
     * 第一句看出这个对象确实是类  top.forethought.jdkknowledge.jvm.MyClassLoader的对象
     * 第二句看出,所属类型检查返回false,这是因为 虚拟机中存在了两个MyClassLoader 类,虽然都来自一个Class 文件,但是使用的
     * 类加载器不同(一个是系统应用程序类加载器(优先使用父类去加载),一个是自定义类加载器(优先使用当前类加载器加载),
     * 这样导致了来自同一个Class 文件,却依然是两个独立的类
     */
}

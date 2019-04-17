package top.forethought.concurrency.threads;
/**
 * @author  wangwei
 * @date     2019/3/31 19:29
 * @classDescription
 *    ThreadLocal 就是线程变量
 *    是以ThreadLocal 对象为键,任意对象为值的存储结构
 *    附带在线程上,也就是说一个线程可以根据ThreadLocal 对象获取到绑定到当前线程上的值

   以下示例演示了 threadLocal 计算方法调用时间
     如果是多个类之间,也可以使用这个方法计算调用耗时
 比如aop:在方法调用前的切点执行begin 方法
   在方法调用后的切点执行end 方法
 */
public class ReadThreadLocal{
    private static final ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public void begin(){
        threadLocal.set(System.currentTimeMillis());
    }
    public Long end(){
        return  System.currentTimeMillis()-threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
       ReadThreadLocal readThreadLocal=new ReadThreadLocal();
       readThreadLocal.begin();
       Thread.sleep(1000);
       System.out.println("方法执行时间:"+readThreadLocal.end()+" mills");
    }
}

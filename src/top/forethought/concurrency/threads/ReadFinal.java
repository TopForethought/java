package top.forethought.concurrency.threads;

public class ReadFinal {
    final int a;
    int b;
    static ReadFinal object;
    public ReadFinal(){
        a=10;//写 final  域
        b=20;// 写普通域
        object=this;
    }



    static void writer(){ // 写线程A 执行
        System.out.println(Thread.currentThread().getName()+ " writer");
        object=new ReadFinal();
    }
    static void reader(){ //读线程B 执行
    System.out.println(Thread.currentThread().getName()+" reader");
        ReadFinal o=object;//读对象引用
        int readA=object.a;// 读 final 域
        int readB=object.b;// 读普通域
    }
    public static void main(String[] args) {
        new Thread(()->{
            object=new ReadFinal();
            writer();
        },"A").start();
        new Thread(()->{
            reader();
        },"B").start();

    }

}

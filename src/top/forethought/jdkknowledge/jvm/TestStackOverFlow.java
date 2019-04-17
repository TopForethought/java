package top.forethought.jdkknowledge.jvm;

/**
 * @author wangwei
 * @date 2019/3/23 20:04
 * @classDescription 测试栈溢出 (java.lang.StackOverflowError)
 * -Xss128k   设置栈最大容量
 *  128k  栈最大深度是 32637
 *  64k   栈最大深度是 25513
 *  32k                17088
 */
public class TestStackOverFlow {
    int stackDepth = 0;

    void recursion() {
        stackDepth++;
        recursion();
    }

    public static void main(String[] args) {
        TestStackOverFlow overFlow = new TestStackOverFlow();
        try {
            overFlow.recursion();
        } catch (Throwable e) {
            System.out.println("stackDepth:" + overFlow.stackDepth);
            throw e;
        }


    }
}

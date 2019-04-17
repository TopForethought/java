package top.forethought.foroffer;
/**
 * @author  wangwei
 * @date     2019/3/16 15:45
 * @classDescription  求整数的平方根
 *
 *
 *    1,根据函数单调性,使用二分求解
 *    2, 根据牛顿逼近迭代法,
 *
 */
public class MySqrt {


    public static void main(String[] args) {
        System.out.println(sqrt1(2,0.0001));
        System.out.println(sqrt2(2,0.0001));
    }
    /**
     *   y=x^2 是单增的
     * @param n   目标值
     * @param p  精度
     * @return
     */
    public  static double sqrt1(int n,double p){
        if(n==0||n==1){
            return n;
        }
        double left=0;
        double right=n;
        double mid=(left+right)/2;

        while(left<right ){
            mid=(left+right)/2;
            if(Math.abs(mid*mid-n)<p){
                break;
            }
            if(mid*mid<n){
                left=mid;
            }
            if(mid*mid>n){
                right=mid;
            }


        }
        return mid;
    }

    /**
     *  牛顿法迭代法
     * @param n
     * @param p  精度
     * @return    x(n+1)=x(n)+a/x(n)   // 这里的a就是被开方数
     *           现在需要求得是 x(n+1)-x(n)的绝对值足够小时,也就是
     */
    public static double sqrt2(int n,double p){
       double  Xn=n/2;
       while (Math.abs(Xn*Xn-n)>p){
           Xn=(Xn+n/Xn)/2;
       }
       return Xn;
    }
}

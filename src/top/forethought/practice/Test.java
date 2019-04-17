package top.forethought.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  wangwei
 * @date     2019/4/1 13:40
 * @classDescription   给定一个数n
 *  求0-n 之间1出现的次数
 *
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new Test().NumberOf1Between1AndN_Solution(117));
        System.out.println(new Test().dpNumberOf1Between1AndN_Solution(117));
    }

    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        while (n > 0) {
            String str = String.valueOf(n);
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1')
                    count++;
            }
            n--;
        }
        return count;
    }

    public int dpNumberOf1Between1AndN_Solution(int n) {

        String str = String.valueOf(n);
        int dp[] = new int[str.length() + 1];
        //dp[0]=0
        //dp[1]=1
        //dp[2]=10*dp[1]+10^(2-1)=20
        // 初始化dp状态
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 10 * dp[i - 1] + myPow(10, i - 1);
        }
        int count = dp[str.length() - 1];
        // 处理输入
        while (true) {

            // 多位数,比如输入 123
            // 处理一次后得到
            // [0-99]V[100,123]
            //         [0,23]
            if(str.length()<1){
                break;
            }
            //获取去掉最高位之前的最高位数字
            int hight=Integer.parseInt(str.substring(0,1));
            str = str.substring(1);//去除最高位
            if(str.length()==0){
                break;
            }
            int current = Integer.parseInt(str);
            if (str.length() > 1) {

             count+=dp[str.length()-1];
            }else{
                count=current>1?count+1:count;
            }


        }

        return count;
    }

    int myPow(int a, int n) {
        int result = 1;
        while (n-- > 0) {
            result *= a;
        }
        return result;
    }
}

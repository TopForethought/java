package top.forethought.foroffer.xiecheng;

import java.util.Scanner;

public class Main001 {

    // 链表环
    // 输入以逗号分隔
    // 有环返回true
    // 无环返回false
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            System.out.println(solution(input));
        }
    }

    // 使用快慢指针,如果追上了,说明有环
    // a b c d e a
    public static boolean solution(String input) {

        String[] chars = input.split(",");

        for (int slow = 0; slow < chars.length; slow++) {
            for (int fast = slow + 1; fast < chars.length; fast++) {
                if (chars[fast].equals(chars[slow])) {
                    return true;
                }
            }
        }

        return false;

    }


}

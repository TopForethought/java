package top.forethought.huawei;

import java.util.Scanner;

public class Test1 {

    private static final int ENCODING_LENGTH = 8;
    private static final String EMPTY_STR = "";
    private static final char  SMALL_HEAD = '0';// 标记为小端

    // 处理程序:
    // 0 :表示后续8个字符小端字符串
    //  1:表示后续8个字符是大端字符串
    // 处理:将所有的编码组转为大端字符串,空格间隔返回
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (true) {//
            int count = Integer.parseInt(in.nextLine());
            String input = in.nextLine();
            String result = solution(count, input);
            System.out.println(result);
        }

    }

    public static String solution(int count, String input) {
        if (null == input || 0 == input.length() || count <= 0) {
            return EMPTY_STR;
        }
        StringBuilder result = new StringBuilder();
        char[] inputArray = input.toCharArray();
        char[] charBuffer = new char[ENCODING_LENGTH];
        char tag;
        for (int i = 0; i < count; i++) {
            tag = inputArray[(ENCODING_LENGTH + 1)* i];
            if (tag == SMALL_HEAD) {
                reverse(inputArray, (ENCODING_LENGTH + 1) * i + 1, (ENCODING_LENGTH + 1) * i +  ENCODING_LENGTH);
            }
            // 拷贝对应区间字符到结果中
            System.arraycopy(inputArray, (ENCODING_LENGTH + 1) * i + 1, charBuffer, 0, ENCODING_LENGTH);
            result.append(charBuffer);
            if (i < count - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    //将会字符数组的字符内容倒序
    public static void reverse(char[] origin, int from, int to) {
        int i = from;
        int j = to;
        char temp;
        while (i < j) {
            temp = origin[j];
            origin[j] = origin[i];
            origin[i] = temp;
            i++;
            j--;

        }

    }
}

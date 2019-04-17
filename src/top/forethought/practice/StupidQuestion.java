package top.forethought.practice;


import java.util.*;

public class StupidQuestion {

    static Map<String, Integer> requestCount = new HashMap<>();
    static Set<String> stupidQues = new HashSet<>();

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while (in.hasNext()){
            requestCount.clear();
            stupidQues.clear();
            int questionNum=in.nextInt();
            int badQuestionNum=in.nextInt();
          //  in.nextLine();
            while (badQuestionNum-->=0){
                String badQuestion=in.nextLine();
                stupidQues.add(badQuestion);
            }
            while (questionNum-->0){
                solution(in.nextLine());
            }

        }
    }





    public static void solution(String input) {
        // 陈述句 不回应
        if (input.endsWith(".")) {
            System.out.println("No Response.");
        }
        // 愚蠢问题
        else if (stupidQues.contains(input)) {
            System.out.println("42.");
        }
        // 非愚蠢问题,问到次数<=5,输出仔细读题
        // 否则回答  Juries are investigating. Thanks.
        else {
            int wrCount = 0;

            if (requestCount.get(input) == null) {

            } else {
                wrCount = requestCount.get(input);
            }
            wrCount += 1;
            requestCount.put(input, wrCount);
            if (wrCount <= 5) {
                System.out.println("Read the problem statement carefully.");
            } else {
                System.out.println("Juries are investigating. Thanks.");
            }
        }
    }
}

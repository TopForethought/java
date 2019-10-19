package top.forethought.pdd;

import java.util.Scanner;
import java.util.Stack;
//()[]  括号合法配对长度
// 枚举起点
// 左括号为起点
// 如果是左括号 入栈
// 如果是右括号，和栈顶不匹配 报错，抛弃该位置
//               与栈顶匹配，弹栈，当前匹配长度+2


public class Problem2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input=in.next();
          System.out.println(solution(input));
        in.close();
    }
   //  字符串 s 的最长合法序列
    public  static int solution(String s){
          if(s==null||s.length()<1){
              return 0;
          }
          // 使用栈 来处理
        int max=0;
          int now=0;
        Stack<Character> stack=new Stack<>();
          for(int i=0;i<s.length();i++){
              char c=s.charAt(i);
              if(c=='('||c=='['){
                  stack.push(c);
                  continue;
              }
              // 处理新元素是 右括号的情况
              if(c==')'){
                  if(!stack.isEmpty()&&stack.peek()!='('){// 全部弹出，此时已经出现匹配失败
                      while (!stack.isEmpty()){
                          stack.pop();
                      }
                      max=max<now?now:max;
                      now=0;
                  }else if(!stack.isEmpty()){ // 配对成功
                      now+=2;
                      stack.pop();//弹出左括号
                  }else{
                      now=0;
                  }

              }
              if(c==']'){
                  if(!stack.isEmpty()&&stack.peek()!='['){// 全部弹出，出现匹配失败
                      while (!stack.isEmpty()){
                          stack.pop();
                      }
                      max=max<now?now:max;
                      now=0;
                  }else if(!stack.isEmpty()){ // 配对
                      now+=2;
                      stack.pop();//弹出左括号
                  }else{
                      now=0;
                  }


              }
              max=max<now?now:max;
          }
          return max;
        }
}

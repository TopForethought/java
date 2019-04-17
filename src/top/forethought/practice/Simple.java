package top.forethought.practice;

import org.springframework.cache.support.SimpleCacheManager;

import java.util.Scanner;

public class Simple {
    public static void main(String[] args) {

        Scanner in=new Scanner(System.in);
        while (in.hasNext()){
          int a= Integer.parseInt(in.nextLine());
          String input=  in.nextLine();
          System.out.println(solution(input));
        }
    }
     static  int solution(String input){
        int []count=new int[26];
        char [] chars=   input.toCharArray();
         int appearMostLetter=0;
         for(char c:chars){
             count[c-'a']++;
             if( count[c-'a']>count[appearMostLetter]){
                 appearMostLetter=c-'a';
             }
         }
         return chars.length-count[appearMostLetter];
    }
}

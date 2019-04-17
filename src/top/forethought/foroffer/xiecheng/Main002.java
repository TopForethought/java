package top.forethought.foroffer.xiecheng;

import java.util.Scanner;

public class Main002 {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            int k = Integer.parseInt(in.nextLine());
            String [] result = solution(input, k);

            System.out.println( array2Str(result,k));
        }
    }

    // k 反转
    public static String [] solution(String input, int k) {

        String[] chars = input.split(",");
        if (chars.length < k) {
            return chars;
        }
        //  StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i += k) {
            reverse(chars,i,i+k-1);
        }

        return chars;
    }
    private  static void reverse(String []input,int start,int k){
        int left=start;
        int right=start+k;
        if(right>=input.length){
            return ;
        }
        while (left<right){
            swap(input,left,right);
            left++;
            right--;
        }


    }
    public static void swap(String [] data,int i,int j){
        String temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }
    //字符串数组转为字符串
    public static String array2Str (String []array,int k){
        StringBuilder str=new StringBuilder();
        int i=0;
        for(;i<array.length;i+=k){
            for(int j=i;j<i+k && j<array.length;j++){
                str.append(array[j])
                        .append(",");
            }
        }

        if(str.lastIndexOf(",")==str.length()-1){
            return   str.substring(0,str.lastIndexOf(","));
        }
        return str.toString();
    }
}




package top.forethought.foroffer;

/**
 * author : wangwei
 * description:  判断回文数  -1 不是 ，121 是
 * date:      2019/8/28
 */
public class Leetcode9 {


//    public boolean isPalindrome(int x) {
//        if(x<0){
//            return false;
//        }
//        if(x>=0 && x<10){
//            return true;
//        }
//          char [] array=(x+"").toCharArray();
//        int left=0;
//        int right=array.length-1;
//        while (left<right){
//            if(array[left]!=array[right]){
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }
    // 不使字符串
    // 将数字翻转一半
    // 后半部分翻转后如果和前半部分相等，那么就是回文数
public boolean isPalindrome(int x) {
   if(x<0){
       return false;
   }
   // 个位为0 只有数字0 满足
   if(x!=0 && x%10==0){
     return false;
   }
   // 假设数字是 n 位（二进制位）
    // 那么第 n 位与 1 位是否应该相同？
  int reverse=0;
   while (reverse<x){
     reverse=reverse*10+x%10;

     x/=10;
    }
    return reverse==x||x==reverse/10;
  }
}

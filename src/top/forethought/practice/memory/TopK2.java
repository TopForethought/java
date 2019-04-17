package top.forethought.practice.memory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class TopK2 {


    public class Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            ArrayList<Integer> result=new ArrayList<>();
            if(k>=input.length){
                for(int i=0;i<input.length;i++){
                    result.add(input[i]);
                }
                return result;

            }
            int mid=partion(input,0,input.length-1);
            while(mid!=k){
                if(mid>k){
                    mid=partion(input,0,mid-1);
                }
                if(mid<k){
                    mid=partion(input,mid+1,input.length-1);
                }
            }

            for(int i=0;i<k;i++){
                result.add(input[i]);
            }

            return result;
        }
        // 使用快排思想

        int partion(int []input,int left,int right){
            if(left>=right){
                return -1;
            }

            int key=input[left];
            // 是为了实现  左侧较小
            // 右侧较大
            while(left<right){
                // 从右往左找 ,停在比key小的位置
                while(left<right && input[right]>key){
                    right--;
                }
                if(left<right){
                    input[left]=input[right];// 小元素放在高位
                }
                // 停在比key大的位置
                while(left<right && input[left]<=key){
                    left++;
                }
                if(left<right){
                    input[right]=input[left];
                }

            }
            input[left]=key;


            return left;
        }
    }
}

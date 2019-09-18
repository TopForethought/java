package top.forethought.foroffer.acwing.dfs_week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组(可能重复）
 * 输出：所有的子集
 * 思路：现将相同元素放到一块，
 * 比如 1 ，2，2，2，3
 * 那么 1 可以选0个，1个
 * 2 可以选0，1，2，3， 个
 * 3 可以选0，1 个
 */

public class Leetcode90Subsets {

    List<List<Object>> finalRes;
    Stack<Object> path;

    public List<List<Object>> solution(Object[] arrs) {
        finalRes = new ArrayList<>();
        path = new Stack<>();
        Arrays.sort(arrs);
        dfs(arrs, 0);
        return finalRes;
    }

    private void dfs(Object[] arrs, int start) {
        if (start == arrs.length) {
            finalRes.add(new ArrayList<>(path));
            return;
        }


        // 统计相同元素个数
        int k = 0;
        while (start + k < arrs.length && arrs[start + k].equals(arrs[start])) {
            k++;
        }
        for (int m = 0; m <= k; m++) {

            dfs(arrs, start + k);
            path.push(arrs[start]);

        }
        //还原现场
        for (int m = 0; m <= k; m++) {
            path.pop();
        }


    }

    public static void main(String[] args) {
        System.out.println(new Leetcode90Subsets().solution(new Object[]{1, 2, 2, 3}));
    }
}

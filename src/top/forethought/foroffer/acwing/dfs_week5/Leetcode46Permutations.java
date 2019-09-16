package top.forethought.foroffer.acwing.dfs_week5;

import java.util.ArrayList;

import java.util.List;

public class Leetcode46Permutations {

    // 全排列 ，无重复元素
    // 思路1; 枚举每个位置能够放的元素  （先确定位置，再确定元素）
    // 思路2：枚举每个元素能够放的位置  ，
    // 需要标记位置是否被占用
    List<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();// 记录一次完整的 排列
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 1) {
            res.add(toList(nums));
            return res;
        }
        used = new boolean[nums.length];
        dfs(nums, 0);

        return res;
    }

    void dfs(int[] nums, int level) { //  填写 第level 层的数字
        if (level == nums.length) {
            res.add((List<Integer>) path.clone());// 已经填满了所有层

            return;
        }
        for (int i = 0; i < used.length; i++) { // 寻找未被使用的位置，该位置 放置nums[i]
            if (!used[i]) { // 如果该位置没有放置数据(此used  数组的使用可以保证 不会出现同一个位置同时被两个不同数据占据）
                used[i] = true;
                path.add(nums[i]);
                dfs(nums, level + 1); // 继续填写下一层
                used[i] = false;//还原现场,放出该位置
                path.remove((Integer) nums[i]);// 路径去掉该元素

            }
        }


    }


    private List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        if (array != null) {
            for (Integer i : array) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode46Permutations().permute(new int[]{1, 2, 3}));
    }
}

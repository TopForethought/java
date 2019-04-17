package top.forethought.practice;
/**
 * @author  wangwei
 * @date     2019/3/17 10:31
 * @classDescription
 *
 *  给定输入数组:
 *   2个数之和为定值  target 的 组合数 (两层循环,或者是用一层循环+hashset)
 *   3个数之和 0 的组合数  (三层循环 或者两层循环+hashset数)
 *   4 个数之和为  0 的组合数 (先排序(升序),
 *            取定  a b
 *            剩余数组两端  c  d
 *             如果a+b+c+d<0
 *             c  往右移
 *             >0
 *             d 往左移
 *             =0, 存在一种
 *             c d 相遇,将b 右移
 *
 *             最后a 又移到倒数第四个位置,结束
 *
 *
 */
public class NSum {


}

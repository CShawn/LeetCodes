package com.cshawn.leetcodes.sword;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4] 
 * 注：[3,1,2,4] 也是正确的答案之一。
 * 提示：
 *     1 <= nums.length <= 50000
 *     1 <= nums[i] <= 10000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author C.Shawn
 * @Date 2020/11/28 20:11
 */
public class Sword_21 {
    public int[] exchange(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        // 用队列存储偶数的索引
        Queue<Integer> evenIndexes = new LinkedList<>();
        // 用栈存储奇数的索引
        Stack<Integer> oddIndexes = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                // 将偶数的索引放入队列
                evenIndexes.offer(i);
            } else {
                // 将奇数的索引放入栈
                oddIndexes.push(i);
            }
        }
        Integer evenIndex = evenIndexes.poll();
        Integer oddIndex = null;
        if (!oddIndexes.isEmpty()) {
            oddIndex = oddIndexes.pop();
        }
        while (evenIndex != null && oddIndex != null) {
            if (evenIndex < oddIndex) {
                // 取出最靠前的偶数与最靠后的奇数交换
                int temp = nums[evenIndex];
                nums[evenIndex] = nums[oddIndex];
                nums[oddIndex] = temp;
                evenIndex = evenIndexes.poll();
            }
            if (!oddIndexes.isEmpty()) {
                oddIndex = oddIndexes.pop();
            } else {
                oddIndex = null;
            }
        }
        return nums;
    }
}

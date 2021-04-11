package com.cshawn.leetcodes.everyday;

import java.util.*;

/**
 * 求出 MK 平均值
 * 给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。
 * MK 平均值 按照如下步骤计算：
 * 如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。
 * 从这个容器中删除最小的 k 个数和最大的 k 个数。
 * 计算剩余元素的平均值，并 向下取整到最近的整数 。
 * 请你实现 MKAverage 类：
 * MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。
 * void addElement(int num) 往数据流中插入一个新的元素 num 。
 * int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。
 *
 * 示例 1：
 * 输入：
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * 输出：
 * [null, null, null, -1, null, 3, null, null, null, 5]
 *
 * 解释：
 * MKAverage obj = new MKAverage(3, 1);
 * obj.addElement(3);        // 当前元素为 [3]
 * obj.addElement(1);        // 当前元素为 [3,1]
 * obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
 * obj.addElement(10);       // 当前元素为 [3,1,10]
 * obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
 *                           // 删除最小以及最大的 1 个元素后，容器为 [3]
 *                           // [3] 的平均值等于 3/1 = 3 ，故返回 3
 * obj.addElement(5);        // 当前元素为 [3,1,10,5]
 * obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
 * obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
 * obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
 *                           // 删除最小以及最大的 1 个元素后，容器为 [5]
 *                           // [5] 的平均值等于 5/1 = 5 ，故返回 5
 * 提示：
 * 3 <= m <= 105
 * 1 <= k*2 < m
 * 1 <= num <= 105
 * addElement 与 calculateMKAverage 总操作次数不超过 105 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/finding-mk-average
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author C.Shawn
 * @date 2021/4/11 11:33 上午
 */
public class Q5729 {
    static class MKAverage {
        // 长度为m的数组用于存在数据
        private final int[] nums;
        private final int[] temp;
        // 记录当前位置，超出m后，index对m取余即可得出新位置，且替换最旧的元素
        private int index = 0;
        private final int k;
        public MKAverage(int m, int k) {
            this.k = k;
            this.nums = new int[m];
            this.temp = new int[m];
        }

        public void addElement(int num) {
            nums[index++ % nums.length] = num;
        }

        public int calculateMKAverage() {
            if (index < nums.length) {
                return -1;
            }
            System.arraycopy(nums, 0, temp, 0, nums.length);
            Arrays.sort(temp);
            long sum = 0;
            for (int i = k; i < temp.length - k; i++) {
                sum += temp[i];
            }
            return (int) (sum / (temp.length - k - k));
        }
    }
}

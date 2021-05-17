package com.cshawn.leetcodes.everyday;

import com.cshawn.leetcodes.sword.TreeNode;

/**
 * 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 *
 * 示例 2：
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 *
 * 示例 3：
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *
 * 提示：
 * 二叉树的节点数介于2 到100之间。
 * 每个节点的值都是唯一的、范围为1 到100的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author C.Shawn
 * @date 2021/5/17 5:07 下午
 */
public class Q993 {
    private int xLevel = 0, yLevel = 0; int xParent = 0, yParent = 0;
    public boolean isCousins(TreeNode root, int x, int y) {
        getLevel(root, x, y, 0, 0);
        return xLevel != 0 && xLevel == yLevel && xParent != yParent;
    }

    private void getLevel(TreeNode root, int x, int y, int level, int parent) {
        if (xLevel != 0 && yLevel != 0 || root == null) {
            return;
        }
        if (root.val == x) {
            xLevel = level;
            xParent = parent;
            return;
        } else if (root.val == y) {
            yLevel = level;
            yParent = parent;
            return;
        }
        getLevel(root.left, x, y, level + 1, root.val);
        getLevel(root.right, x, y, level + 1, root.val);
    }
}

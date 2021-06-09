package com.cshawn.leetcodes.everyday;

/**
 * 盈利计划
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * 
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * 
 * 示例 1：
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 
 * 示例 2：
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 * 
 * 提示：
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/profitable-schemes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q879 {
    // 动态规划
    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        // dp[i][j][k] 表示在前i个工作中选择了j个员工，并且满足工作利润至少为k的情况下的盈利计划的总数目
        int[][][] dp = new int[group.length + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % 1000000007;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[group.length][j][minProfit]) % 1000000007;
        }
        return sum;
    }

    // 优化空间：滚动数组
    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[2][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= group.length; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i & 1][j][k] = dp[(i & 1) ^ 1][j][k];
                    } else {
                        dp[i & 1][j][k] = (dp[(i & 1) ^ 1][j][k] + dp[(i & 1) ^ 1][j - members][Math.max(0, k - earn)]) % 1000000007;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[(group.length & 1)][j][minProfit]) % 1000000007;
        }
        return sum;
    }

    // 优化空间：倒序遍历
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= group.length; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % 1000000007;
                }
            }
        }
        return dp[n][minProfit];
    }
}

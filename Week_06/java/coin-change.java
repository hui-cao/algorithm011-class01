/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (40.02%)
 * Likes:    720
 * Dislikes: 0
 * Total Accepted:    110.1K
 * Total Submissions: 271.4K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 
 * 示例 2:
 * 
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 * 
 * 
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * 
 */

// @lc code=start
class Solution {
    /**
     * DP
     * time -> O(m*n) m: coins.length, n: amount
     * space -> O(n)
     * @author caohui
     * @date 2020/07/28
     */
    public int coinChange(int[] coins, int amount) {
        int[] opt = new int[amount+1];
        for (int a = 1; a < opt.length; a++) {
            opt[a] = amount + 1; // 避免 Integer.MAX_VALUE + 1 溢出为负值
            for (int coin : coins) {
                if (a >= coin) opt[a] = Math.min(opt[a], opt[a - coin] + 1);
            }
        }
        return opt[opt.length-1] == amount + 1 ? -1 : opt[opt.length-1];
    }
}
// @lc code=end


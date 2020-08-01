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
     * DP 自下而上
     * time -> O(m*n) m: amount, n: coins.length. runtime beats 90.41 %
     * space -> O(n)
     * @author caohui
     * @date 2020/07/28
     */
    public int coinChange(int[] coins, int amount) {
        int[] opt = new int[amount+1];
        for (int a = 1; a < opt.length; a++) { // 从 1 开始以保证opt[0] -> 0
            opt[a] = amount + 1; // 避免 Integer.MAX_VALUE + 1 溢出为负值
            for (int coin : coins) {
                if (a >= coin) opt[a] = Math.min(opt[a], opt[a - coin] + 1);
            }
        }
        return opt[opt.length-1] > amount ? -1 : opt[opt.length-1];
    }

    /**
     * BFS 自上而下
     * time -> time -> O(m*n) m: amount, n: coins.length. runtime beats 5.01 % 
     * space -> O(m)
     * @author caohui
     * @date 2020/07/31
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(amount);
        int level = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int a = queue.remove();
                for (int c : coins) {
                    if (a - c == 0) return level + 1;
                    if (a - c > 0 && !visited.contains(a - c)) {
                        queue.add(a - c);
                        visited.add(a - c); // 尽早放入缓存，否则会超时
                    }
                }
            }
            level++;
        }
        return -1;
    }

    /**
     * DFS with memo 自上而下
     * time -> O(m*n) m: amount, n: coins.length. runtime beats 15.58 %
     * space -> O(m)
     * @author caohui
     * @date 2020/07/31
     */
    public int coinChange2(int[] coins, int amount) {
        return dfs(coins, amount, new int[amount+1]);
    }

    private int dfs(int[] coins, int amount, int[] memo) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (memo[amount] != 0) return memo[amount];
        
        int min = amount + 1;
        for (int c : coins) {
            int res = dfs(coins, amount - c, memo);
            if (res != -1) min = Math.min(min, res + 1);
        }
        
        memo[amount] = min > amount ? -1 : min;
        return memo[amount];
    }

    /**
     * 暴力枚举
     * time -> O(m^n) m: amount, n: coins.length
     * space -> O(m) m: 递归栈的深度
     * @author caohui
     * @date 2020/07/31
     */
    public int coinChange1(int[] coins, int amount) {
        return coinChange(coins, amount, 0);
    }

    private int coinChange(int[] coins, int amount, int coinsIdx) {
        if (amount == 0) return 0;
        if (amount < 0 || coinsIdx >= coins.length) return -1;

        int coinMaxNum = amount / coins[coinsIdx];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= coinMaxNum; i++) {
            int res = coinChange(coins, amount - i * coins[coinsIdx], coinsIdx + 1);
            if (res != -1) min = Math.min(min, res + i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
// @lc code=end


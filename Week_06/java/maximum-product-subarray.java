/*
 * @lc app=leetcode.cn id=152 lang=java
 *
 * [152] 乘积最大子数组
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/description/
 *
 * algorithms
 * Medium (39.72%)
 * Likes:    686
 * Dislikes: 0
 * Total Accepted:    83.7K
 * Total Submissions: 209.1K
 * Testcase Example:  '[2,3,-2,4]'
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 
 */

// @lc code=start
class Solution {
    /**
     * DP
     * time -> O(n)
     * space -> O(1)
     * @author caohui
     * @date 2020/08/04
     */
    public int maxProduct(int[] nums) {
        int currMin = 1, currMax = 1;
        int res = nums[0];
        for (int n : nums) {
            if (n < 0) {
                int tmp = currMin;
                currMin = currMax;
                currMax = tmp;
            }
            currMin = Math.min(currMin * n, n);
            currMax = Math.max(currMax * n, n);
            res = Math.max(res, currMax);
        }
        return res;
    }

    /**
     * DP
     * time -> O(n)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/04
     */
    public int maxProduct1(int[] nums) {
        if (nums.length == 1) return nums[0];

        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        int res = dp[0][1];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
            }
            else {
                dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
            }
            res = Math.max(res, dp[i][1]);
        }

        return res;
    }
}
// @lc code=end


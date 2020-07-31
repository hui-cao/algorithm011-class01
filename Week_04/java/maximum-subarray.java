/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (51.48%)
 * Likes:    2207
 * Dislikes: 0
 * Total Accepted:    283.8K
 * Total Submissions: 547.8K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 示例:
 * 
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 * 
 * 进阶:
 * 
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * 
 */

// @lc code=start
class Solution {
    /**
     * DP
     * time -> O(n)
     * space -> O(1)
     * @author caohui
     * @date 2020/07/31
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum, 0) + nums[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * DP
     * time -> O(n)
     * space -> O(n)
     * @author caohui
     * @date 2020/07/31
     */
    public int maxSubArray3(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // dp[i] means the maximum-subarray ending with nums[i]
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 分治
     * 以mid为中心向左右逐步扩大至边界，记录连续数组的最大和。
     * 假设目标不存在则目标（连续数组）存在于左半边或右半边，把左、右半边作为同类子问题进行递归。
     * time -> O(nlogn)
     * space -> O(1)
     * @author caohui
     * @date 2020/07/23
     */
    public int maxSubArray2(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    private int recursion(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int mid = (left + right) >>> 1;
        int leftSum = 0, leftMax = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            leftSum  += nums[i];
            leftMax = Math.max(leftSum, leftMax);
        }
        int rightSum = 0, rightMax = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightSum, rightMax);
        }
        return Math.max(leftMax+rightMax, Math.max(
            recursion(nums, left, mid), 
            recursion(nums, mid + 1, right) 
        ));
    }

    /**
     * 暴力枚举
     * time -> O(n^2)
     * space -> O(1)
     * @author caohui
     * @date 2020/07/23
     */
    public int maxSubArray1(int[] nums) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                res = Math.max(res, sum);
            }
        }
        return res;
    }
}
// @lc code=end


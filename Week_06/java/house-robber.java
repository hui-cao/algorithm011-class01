/*
 * @lc app=leetcode.cn id=198 lang=java
 *
 * [198] 打家劫舍
 *
 * https://leetcode-cn.com/problems/house-robber/description/
 *
 * algorithms
 * Easy (45.69%)
 * Likes:    967
 * Dislikes: 0
 * Total Accepted:    160K
 * Total Submissions: 347.1K
 * Testcase Example:  '[1,2,3,1]'
 *
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 2：
 * 
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * DP
     * time -> O(n)
     * space -> O(1)
     * @author caohui
     * @date 2020/08/01
     */
    public int rob(int[] nums) {
        int curr = 0;
        int prev = 0;
        for (int i : nums) {
            int tmp = Math.max(curr, prev + i);
            prev = curr;
            curr = tmp;
        }
        return curr;
    }

    /**
     * DP
     * time -> O(n)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/01
     */
    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        
        int[] a = new int[n];
        a[0] = nums[0];
        a[1] = Math.max(nums[0], nums[1]);

        for(int i = 2; i < n; i++) {
            a[i] = Math.max(a[i - 1], a[i - 2] + nums[i]);
        }

        return Math.max(a[n-1], a[n-2]);
    }

    /**
     * DP
     * time -> O(n)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/01
     */
    public int rob1(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        // a[i] 为从0直至i偷到的最高总金额。a[i][0] 当前不偷；a[i][1] 当前要偷
        int[][] a = new int[n][2];
        a[0][0] = 0;
        a[0][1] = nums[0];

        for(int i = 1; i < n; i++) {
            a[i][0] = Math.max(a[i - 1][0], a[i - 1][1]);
            a[i][1] = a[i - 1][0] + nums[i];
        }

        return Math.max(a[n-1][0], a[n-1][1]);
    }
}
// @lc code=end


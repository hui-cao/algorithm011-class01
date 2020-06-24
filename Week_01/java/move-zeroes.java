/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 * problem: https://leetcode-cn.com/problems/move-zeroes/
 * @author caohui
 * @date 2020/06/24
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        assert nums != null;
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != slow) {
                    nums[slow] = nums[i];
                    nums[i] = 0;
                }
                slow++;
            }
        }
    }
}
// @lc code=end


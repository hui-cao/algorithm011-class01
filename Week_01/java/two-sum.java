/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 * problem: https://leetcode-cn.com/problems/two-sum/
 * @author caohui
 * @date 2020/06/24
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        java.util.Map<Integer, Integer> numIndices = new java.util.HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int anotherNum = target - nums[i];
            if (numIndices.containsKey(anotherNum)) {
                return new int[]{numIndices.get(anotherNum), i};
            }
            numIndices.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");
    }
}
// @lc code=end


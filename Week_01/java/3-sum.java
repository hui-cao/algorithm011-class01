/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 3Sum
 * problem: https://leetcode-cn.com/problems/3-sum/
 * @author caohui
 * @date 2020/06/24
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        if (nums[0] > 0) return result;

        for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int l = i+1, r = nums.length-1, sum = 0;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }  
                else if (sum < 0) l++;
                else r--;
            }
        }

        return result;
    }
}
// @lc code=end


/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (59.17%)
 * Likes:    337
 * Dislikes: 0
 * Total Accepted:    68.9K
 * Total Submissions: 116.1K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 * 
 * @author caohui
 * @date 2020/07/07
 */

// @lc code=start
class Solution {
    /**
     * 回溯
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length = 0) return res;

        Arrays.sort(nums);
        dfs(res, nums, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, 
                     LinkedList<Integer> cur, boolean[] used) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue; // !used[i-1] 通过状态重置判定其已完成了回溯
            
            cur.add(nums[i]);
            used[i] = true;
            dfs(res, nums, cur, used);
            cur.removeLast();
            used[i] = false;
        }
    }
}
// @lc code=end


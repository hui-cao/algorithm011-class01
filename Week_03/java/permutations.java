/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (76.25%)
 * Likes:    777
 * Dislikes: 0
 * Total Accepted:    148.8K
 * Total Submissions: 195K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 * @author caohui
 * @date 2020/07/06
 */

// @lc code=start
class Solution {
    /**
     * 回溯
     * 时间复杂度 O(n * n!)
     * 空间复杂度 O(n * n!)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(res, nums, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, LinkedList<Integer> cur, boolean[] used) {
        if (cur.size() == nums.length) {
            res.add(new LinkedList<>(cur)); //注意此处需要复制一份，cur 会在整个递归树上传递
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            cur.add(nums[i]); // 隐含了 depth + 1 的动作
            used[i] = true;
            dfs(res, nums, cur, used);
            // backtrack
            cur.removeLast();
            used[i] = false;
        }
    }
}
// @lc code=end


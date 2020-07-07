/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (73.90%)
 * Likes:    302
 * Dislikes: 0
 * Total Accepted:    59K
 * Total Submissions: 79.7K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
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
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (n == 0 || k == 0 || n < k) return res;
        backtrack(res, n, k, 1, new LinkedList<>());
        return res;
    }

    public void backtrack(List<List<Integer>> res, int n, int k, int start, LinkedList<Integer> cur) {
        if (cur.size() == k) {
            res.add(new LinkedList(cur));
        }

        for (int i = start; i <= n; i++) {
            cur.add(i);
            backtrack(res, n, k, i + 1, cur);
            cur.removeLast();
        }
        
    }
}
// @lc code=end


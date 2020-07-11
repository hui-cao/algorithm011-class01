/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (75.60%)
 * Likes:    1142
 * Dislikes: 0
 * Total Accepted:    144.9K
 * Total Submissions: 191.5K
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：n = 3
 * 输出：[
 * ⁠      "((()))",
 * ⁠      "(()())",
 * ⁠      "(())()",
 * ⁠      "()(())",
 * ⁠      "()()()"
 * ⁠    ]
 * 
 * @author caohui
 * @date 2020/07/07
 */

// @lc code=start
class Solution {
    /**
     * 回溯
     * 时间复杂度 O(4^n/sqrt(n))
     * 空间复杂度 O(n) 取决于递归栈的深度
     */
    public List<String> generateParenthesis1(int n) {
        List<String> res = new LinkedList<>();
        backtrack(res, 0, 0, n, "");
        return res;
    }

    private void backtrack(List<String> res, int left, int right, int max, String cur) {
        if (left == max && right == max) {
            res.add(cur);
            return;
        }

        if (left < max) backtrack(res, left + 1, right, max, cur + "(");
        if (right < left) backtrack(res, left, right + 1, max, cur + ")");
    }

    /**
     * 暴力递归
     * 时间复杂度 O(2^(2n) * n)
     * 空间复杂度 O(n) 取决于递归栈的深度
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        generateAll(res, 0, n*2, "");
        return res;
    }

    private void generateAll(List<String> res, int pos, int max, String cur) {
        if (pos == max) {
            if (valid(cur)) res.add(cur);
        }
        else {
            generateAll(res, pos + 1, max, cur + "(");
            generateAll(res, pos + 1, max, cur + ")");
        }
    }

    private boolean valid(String cur) {
        int balance = 0;
        for (char c : cur.toCharArray()) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}
// @lc code=end


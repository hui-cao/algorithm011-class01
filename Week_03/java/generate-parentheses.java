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
     * DP
     * time | runtime beats 8.42 % 
     * @author caohui
     * @date 2020/08/06
     */
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>(n);
        dp.add(Arrays.asList("")); // dp(0)
        for (int i = 1; i <= n; i++) {
            List<String> dpi = new LinkedList<>();
            for (int j = 0; j <= i - 1; j++) {
                // dp(i) = ( + dp((i-1)-j) + ) + dp(j); j: [0 -> i-1]
                List<String> strs1 = dp.get(i-1-j);
                List<String> strs2 = dp.get(j);
                for (String s1 : strs1) for (String s2 : strs2) dpi.add("(" + s1 + ")" + s2);
            }
            dp.add(dpi);
        }
        return dp.get(n);
    }

    /**
     * BFS
     * time | runtime beats 16.60 % 
     * @author caohui
     * @date 2020/07/16
     */
    public List<String> generateParenthesis3(int n) {
        List<String> res = new LinkedList<>();
        if (n == 0) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node("", 0, 0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            if (cur.left == n && cur.right == n) {
                res.add(cur.res);
                continue;
            }
            if (cur.left < n) queue.add(new Node(cur.res + "(", cur.left + 1, cur.right));
            if (cur.right < cur.left) queue.add(new Node(cur.res + ")", cur.left, cur.right + 1));
        }

        return res;
    }

    class Node {
        private String res;
        private int left;
        private int right;
        public Node (String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 回溯 (DFS)
     * 时间复杂度 O(4^n/sqrt(n)) | runtime beats 96.93 % 
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
     * 时间复杂度 O(2^(2n) * n) | runtime beats 5.29 % 
     * 空间复杂度 O(n) 取决于递归栈的深度
     */
    public List<String> generateParenthesis2(int n) {
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


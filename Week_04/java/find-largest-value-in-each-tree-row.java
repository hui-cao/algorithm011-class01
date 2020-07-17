/*
 * @lc app=leetcode.cn id=515 lang=java
 *
 * [515] 在每个树行中找最大值
 *
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/description/
 *
 * algorithms
 * Medium (60.48%)
 * Likes:    74
 * Dislikes: 0
 * Total Accepted:    14.4K
 * Total Submissions: 23.6K
 * Testcase Example:  '[1,3,2,5,3,null,9]'
 *
 * 您需要在二叉树的每一行中找到最大的值。
 * 
 * 示例：
 * 
 * 
 * 输入: 
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       3   2
 * ⁠      / \   \  
 * ⁠     5   3   9 
 * 
 * 输出: [1, 3, 9]
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * BFS
     * @author caohui
     * @date 2020/07/17
     */
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.remove();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                max = Math.max(max, cur.val);
            }
            res.add(max);
        }

        return res;
    }

    /**
     * DFS
     * @author caohui
     * @date 2020/07/17
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode cur, int level) {
        if (cur == null) return;
        if (res.size() == level) res.add(cur.val);
        else res.set(level, Math.max(res.get(level), cur.val));
        dfs(res, cur.left, level + 1);
        dfs(res, cur.right, level + 1);
    }
}
// @lc code=end


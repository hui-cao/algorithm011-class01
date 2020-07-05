/*
 * @lc app=leetcode.cn id=144 lang=java
 *
 * [144] 二叉树的前序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
 *
 * algorithms
 * Medium (65.86%)
 * Likes:    297
 * Dislikes: 0
 * Total Accepted:    125.6K
 * Total Submissions: 190.5K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]  
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3 
 * 
 * 输出: [1,2,3]
 * 
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 * @author caohui
 * @date 2020/07/05
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
     * 借助栈进行遍历，模拟递归
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            root = stack.removeFirst();
            res.add(root.val);
            if (root.right != null) stack.addFirst(root.right);
            if (root.left != null) stack.addFirst(root.left);
        }

        return res;
    }

    /**
     * 递归
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursion(root, res);
        return res;
    }

    private void recursion(TreeNode node, List<Integer> res) {
        if (node != null) {
            res.add(node.val);
            if (node.left != null) recursion(node.left, res);
            if (node.right != null) recursion(node.right, res);
        }
    }
}
// @lc code=end


/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (71.79%)
 * Likes:    551
 * Dislikes: 0
 * Total Accepted:    183.4K
 * Total Submissions: 255.1K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的中序 遍历。
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
 * 输出: [1,3,2]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
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
     * 递归
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursion(root, res);
        return res;
    }

    private void recursion(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) recursion(root.left, res);
            res.add(root.val);
            if (root.right != null) recursion(root.right, res);
        }
    }

    /**
     * 借助栈进行迭代，模拟递归
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            // 左
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            // 根
            root = stack.removeFirst();
            res.add(root.val);
            // 右
            root = root.right;
        }

        return res;
    }
}
// @lc code=end


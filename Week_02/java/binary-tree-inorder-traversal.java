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
 * @author caohui
 * @date 2020/07/04
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
    public List<Integer> inorderTraversal2(TreeNode root) {
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

    /**
     * 莫里斯遍历（中序）
     *
     * 若current没有左子节点
     *     a. 将current添加到输出
     *     b. 进入右子树，亦即, current = current.right
     * 否则
     *     a. 在current的左子树中，令current成为最右侧节点的右子节点
     *     b. 进入左子树，亦即，current = current.left
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur!=null) {
            if (cur.left == null) { // 若 current 没有左子节点
                // 将 current 添加到输出
                res.add(cur.val);
                // 进入右子树，亦即, current = current.right
                cur = cur.right;
            }
            else {
                pre = cur.left; // 左子树
                // 找到前驱节点，即左子树中的最右节点
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 如果前驱节点的右子节点为空，将它的右子节点设置为当前节点。当前节点更新为当前节点的左子节点。
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右子节点。
                if (pre.right == cur) {
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
// @lc code=end


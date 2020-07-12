/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (67.27%)
 * Likes:    559
 * Dislikes: 0
 * Total Accepted:    93.4K
 * Total Submissions: 138.5K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 
 * 例如，给出
 * 
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 
 * 返回如下的二叉树：
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 */

// @lc code=start
/**
 * @author caohui
 * @date 2020/07/11
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) throw new IllegalArgumentException("different length");
        if (preorder.length == 0) return null;

        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return recursion(map, preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode recursion(Map<Integer, Integer> map, 
                               int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        int preRoot = preStart;
        TreeNode root = new TreeNode(preorder[preRoot]);
        int inRoot = map.get(preorder[preRoot]);
        int sizeLeftSubTree = inRoot - inStart;

        root.left = recursion(map, preorder, preStart + 1, preStart + sizeLeftSubTree, inorder, inStart, inRoot - 1);
        root.right = recursion(map, preorder, preStart + 1 + sizeLeftSubTree, preEnd, inorder, inRoot + 1, inEnd);
        return root;
    }
}
// @lc code=end


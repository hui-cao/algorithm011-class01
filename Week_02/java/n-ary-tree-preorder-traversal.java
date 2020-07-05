/*
 * @lc app=leetcode.cn id=589 lang=java
 *
 * [589] N叉树的前序遍历
 *
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
 *
 * algorithms
 * Easy (73.27%)
 * Likes:    86
 * Dislikes: 0
 * Total Accepted:    32.1K
 * Total Submissions: 43.6K
 * Testcase Example:  '[1,null,3,2,4,null,5,6]'
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * 
 * 例如，给定一个 3叉树 :
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * 
 * 
 * 
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * 
 * @author caohui
 * @date 2020/07/03
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    /**
     * 递归
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        recursion(root, res);
        return res;
    }

    private void recursion(Node node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        if (node.children != null) {
            for (Node n : node.children) {
                recursion(n, res);
            }
        }
    }

    /**
     * 借助栈进行迭代，模拟递归
     */
    public List<Integer> preorder1(Node root) {
        List<Integer> res = new LinkedList();
        if (root == null) return res;

        Deque<Node> stack = new LinkedList<>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            // 根
            root = stack.removeFirst();
            res.add(root.val);
            // 左 - 右
            if (root.children != null) {
                // 逆序入栈
                for (int i = root.children.size()-1; i >= 0; i--) {
                    stack.addFirst(root.children.get(i));
                }
            }
        }

        return res;
    }

}
// @lc code=end


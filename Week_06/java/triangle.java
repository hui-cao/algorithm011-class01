/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * https://leetcode-cn.com/problems/triangle/description/
 *
 * algorithms
 * Medium (64.95%)
 * Likes:    549
 * Dislikes: 0
 * Total Accepted:    96.3K
 * Total Submissions: 144.4K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 
 * 
 * 
 * 例如，给定三角形：
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 
 * 
 * 
 * 说明：
 * 
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * 
 */

// @lc code=start
class Solution {
    /**
     * recursion without memo | top-down
     * time -> O(2^n)
     * space -> O(n)
     * @author caohui
     * @date 2020/07/29
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        return recur(triangle, 0, 0);
    }

    private int recur(List<List<Integer>> triangle, int cur, int level) {
        if (level == triangle.size() - 1) return triangle.get(level).get(cur);
        int leftSum = recur(triangle, cur, level + 1);
        int rightSum = recur(triangle, cur + 1, level + 1);
        return triangle.get(level).get(cur) + Math.min(leftSum, rightSum);
    }

    /**
     * DP | bottom-up
     * time -> O(n^2)
     * space -> O(n)
     * @author caohui
     * @date 2020/07/29
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        Integer[] dp = triangle.get(triangle.size()-1).toArray(new Integer[triangle.size()]);

        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }

    /**
     * DP without copying | bottom-up
     * @author caohui
     * @date 2020/07/29
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()+1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }
}
// @lc code=end


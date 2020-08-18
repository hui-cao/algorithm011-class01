/*
 * @lc app=leetcode.cn id=52 lang=java
 *
 * [52] N皇后 II
 *
 * https://leetcode-cn.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (79.18%)
 * Likes:    140
 * Dislikes: 0
 * Total Accepted:    29.8K
 * Total Submissions: 37.6K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或
 * N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 * 
 * 
 */

// @lc code=start
class Solution {
    private int count;
    private int mask;

    /**
     * dfs 回溯 + bit
     * @author caohui
     * @date 2020/08/18
     */
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        mask = (1 << n) - 1; // 将n个低位设置为1
        dfsbit(n, 0, 0, 0, 0);
        return count;
    }

    private void dfsbit(int n, int row, int cols, int pie, int na) {
        if (row == n) {
            count++;
            return;
        }

        int free = ~(cols | pie | na) & mask; // 当前（第 row 行）可以放置的所有位置
        while (free != 0) {
            int p = free & -free; // 取最低位的1
            dfsbit(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1);
            free &= free - 1; // 对为1的最低位清零
        }
    }

    /**
     * dfs 回溯
     * @author caohui
     * @date 2020/08/18
     */
    public int totalNQueens1(int n) {
        if (n <= 0) return 0;
        dfs(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return this.count;
    }

    private void dfs(int n, int row, Set<Integer> cols, Set<Integer> pie, Set<Integer> na) {
        if (row == n) {
            this.count++;
            return;
        }

        for (int c = 1; c <= n; c++) {
            if (cols.contains(c) || pie.contains(row + c) || na.contains(row - c)) continue;
            
            cols.add(c);
            pie.add(row + c);
            na.add(row - c);

            dfs(n, row + 1, cols, pie, na);

            cols.remove(c);
            pie.remove(row + c);
            na.remove(row - c);
        }
    }
}
// @lc code=end


/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (70.05%)
 * Likes:    456
 * Dislikes: 0
 * Total Accepted:    48.4K
 * Total Submissions: 69K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自
 * 百度百科 - 皇后 ）
 * 
 */

// @lc code=start
class Solution {
    /**
     * 回溯 + bit
     * @author caohui
     * @date 2020/08/18
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        if (n <= 0) return res;
        dfsbit(res, new int[n], n, 0, 0, 0, 0);
        return res;
    }

    private void dfsbit(List<List<String>> res, int[] queens, int n, int row, int cols, int pie, int na) {
        if (row == n) {
            res.add(draw(queens));
            return;
        }
        int mask = (1 << n) - 1;
        int free = ~(cols | pie | na) & mask;
        while (free != 0) {
            int p = free & -free;
            
            int temp = p, i = 0;
            while ((temp >>= 1) != 0) i++;
            queens[row] = i;
            
            dfsbit(res, queens, n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1);
            
            free &= free - 1;
        }
    }

    private List<String> draw(int[] queens) {
        List<String> lines = new LinkedList<>();
        for (int q : queens) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < queens.length; i++) line.append(i == q ? "Q" : ".");
            lines.add(line.toString());
        }
        return lines;
    }

    /**
     * 回溯
     * @author caohui
     * @date 2020/07/08
     */
    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> res = new LinkedList<>();
        if (n <= 0) return res;
        dfs(res, n, 0, new LinkedHashSet<>(), new HashSet<>(), new HashSet<>());
        return res;
    }

    private void dfs(List<List<String>> res, int n, int row,
                     LinkedHashSet<Integer> column, Set<Integer> pie, Set<Integer> na) {
        if (row == n) {
            res.add(draw(column));
            return;
        }
        // each columns
        for (int i = 1; i <= n; i++) {
            if (column.contains(i) || pie.contains(row + i) || na.contains(row - i)) continue;

            column.add(i);
            pie.add(row + i);
            na.add(row - i);

            dfs(res, n, row + 1, column, pie, na);

            column.remove(i);
            pie.remove(row + i);
            na.remove(row - i);
        }
    }

    private List<String> draw(LinkedHashSet<Integer> column) {
        return column.stream().map(col -> 
            IntStream.rangeClosed(1, column.size()).mapToObj(i -> i == col ? "Q" : ".").collect(Collectors.joining(""))
        ).collect(Collectors.toList());
    }
}
// @lc code=end


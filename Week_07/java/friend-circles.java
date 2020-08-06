/*
 * @lc app=leetcode.cn id=547 lang=java
 *
 * [547] 朋友圈
 *
 * https://leetcode-cn.com/problems/friend-circles/description/
 *
 * algorithms
 * Medium (57.54%)
 * Likes:    285
 * Dislikes: 0
 * Total Accepted:    55.7K
 * Total Submissions: 96.7K
 * Testcase Example:  '[[1,1,0],[1,1,0],[0,0,1]]'
 *
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是
 * C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j
 * 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * 
 * 示例 1:
 * 
 * 
 * 输入: 
 * [[1,1,0],
 * ⁠[1,1,0],
 * ⁠[0,0,1]]
 * 输出: 2 
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: 
 * [[1,1,0],
 * ⁠[1,1,1],
 * ⁠[0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 
 * 
 * 注意：
 * 
 * 
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * Disjoint Set
     * time -> O(n^3)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/05
     */
    public int findCircleNum(int[][] M) {
        int count = M.length, parent[] = IntStream.range(0, M.length).toArray();
        for (int i = 0; i < M.length - 1; i++) {
            for (int j = i + 1; j < M.length; j++) {
                int iRoot = findRoot(parent, i), jRoot = findRoot(parent, j);
                if (M[i][j] == 1 && iRoot != jRoot) {
                    parent[jRoot] = iRoot;
                    count--;
                }
            }
        }
        return count;
    }

    private int findRoot(int[] parent, int i) {
        while (i != parent[i]) i = parent[i] = parent[parent[i]];
        return i;
    }

    /**
     * Disjoint Set
     * time -> O(n^3)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/05
     */
    public int findCircleNum3(int[][] M) {
        DisjointSet d = new DisjointSet(M.length);
        for (int i = 0; i < M.length - 1; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) d.union(i, j);
            }
        }
        return d.count;
    }

    class DisjointSet {
        private int count;
        private int[] parent;

        public DisjointSet(int n) {
            this.count = n;
            parent = new int[n];
            IntStream.range(0, n).forEach(i -> parent[i] = i);
        }

        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public void union(int i, int j) {
            int iRoot = find(i), jRoot = find(j);
            if (iRoot != jRoot) {
                parent[iRoot] = jRoot;
                count--;
            }
        }

    }

    /**
     * BFS
     * time -> O(n^2)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/05
     */
    public int findCircleNum2(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                q.add(i);
                while (!q.isEmpty() && i < M.length) {
                    int cur = q.remove();
                    for (int j = 0; j < M.length; j++) {
                        if (M[cur][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
    
    /**
     * DFS
     * time -> O(n^2)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/05
     */
    public int findCircleNum1(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(visited, M, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(boolean[] visited, int[][] M, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(visited, M, j);
            }
        }
    }
    
}
// @lc code=end


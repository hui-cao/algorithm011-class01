/*
 * @lc app=leetcode.cn id=212 lang=java
 *
 * [212] 单词搜索 II
 *
 * https://leetcode-cn.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (40.91%)
 * Likes:    208
 * Dislikes: 0
 * Total Accepted:    18.2K
 * Total Submissions: 43.5K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n' +
  '["oath","pea","eat","rain"]'
 *
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 
 * 
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 
 * 示例:
 * 
 * 输入: 
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * 
 * 输出: ["eat","oath"]
 * 
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * 
 * 提示:
 * 
 * 
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？
 * 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * Trie + DFS
     * time -> O(m*n*4*3^(l-1)) m: board.length; n: board[0].length; l: word.length()
     * @author caohui
     * @date 2020/08/05
     */
    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0) return new ArrayList();
        Set<String> res = new HashSet<>();

        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(res, board, i, j, "", root);
            }
        }
        return new ArrayList(res);
    }

    private static int[] dx = new int[]{-1, 1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};
    private static final char USED = '-';

    private void dfs(Set<String> res, char[][]board, int i, int j, String curr, Trie trieCurr) {
        char c = board[i][j];
        trieCurr = trieCurr.get(c);
        if (trieCurr == null) return;
        
        curr += Character.toString(c);
        board[i][j] = USED;
        if (trieCurr.isEnd) res.add(curr); // 无需终止，不同的单词可以重复使用字母

        int m = board.length, n = board[0].length;
        for (int k = 0; k < 4; k++) {
            int x = j + dx[k], y = i + dy[k];
            if (x >= 0 && x < n && y >= 0 && y < m && board[y][x] != USED)
                dfs(res, board, y, x, curr, trieCurr);
        }

        board[i][j] = c;
    }

    static class Trie {
        private Trie[] children;
        private boolean isEnd;
        
        /** Initialize your data structure here. */
        public Trie() {
            this.children = new Trie[26];
        }

        private Trie get(char key) {
            return children[key - 'a'];
        }

        private Trie putIfAbsent(char key, Trie node) {
            Trie v = get(key);
            if (v == null) {
                children[key - 'a'] = node;
                v = node;
            }
            return v;
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                node.putIfAbsent(c, new Trie());
                node = node.get(c);
            }
            node.isEnd = true;
        }
    }
}

// @lc code=end


/*
 * @lc app=leetcode.cn id=126 lang=java
 *
 * [126] 单词接龙 II
 *
 * https://leetcode-cn.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (38.39%)
 * Likes:    287
 * Dislikes: 0
 * Total Accepted:    20.6K
 * Total Submissions: 53.6K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: []
 * 
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * 
 */

// @lc code=start
class Solution {
    /**
     * BFS
     * @author caohui
     * @date 2020/07/18
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return res;

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(beginWord));
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Set<String> nextLevelVisited = new HashSet<>();
            for (int i = queue.size(); i > 0; i--) {
                List<String> path = queue.remove();
                String word = path.get(path.size() - 1);
                
                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (word.charAt(j) == c) continue;
                        String newWord = new StringBuilder(word).replace(j, j+1, Character.toString(c)).toString();
                        
                        if (wordSet.contains(newWord)) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(newWord);
                            queue.add(newPath);
                            if (newWord.equals(endWord)) {
                                found = true;
                                res.add(newPath);
                            }
                            nextLevelVisited.add(newWord);
                        }
                    }
                }
            }
            wordSet.removeAll(nextLevelVisited);
        }

        return res;
    }

    /**
     * BFS + DFS
     * @author caohui
     * @date 2020/07/17
     */
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        if (wordList.size() == 0) return res;
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return res;

        Node begin = new Node(beginWord);
        boolean found = bfs(begin, endWord, wordSet);
        Deque<String> stack = new LinkedList<>();
        stack.addLast(begin.word);
        dfs(res, begin, endWord, stack);
        return res;
    }

    private boolean bfs(Node begin, String endWord, Set<String> wordSet) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(begin);
        boolean found = false;
        while (!queue.isEmpty()) {
            Set<String> nextLevelVisited = new HashSet<>();
            for (int i = queue.size(); i > 0; i--) {
                Node cur = queue.remove();
                for (int j = 0; j < cur.word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (cur.word.charAt(j) == c) continue;
                        String newWord = new StringBuilder(cur.word).replace(j, j+1, Character.toString(c)).toString();
                        if (endWord.equals(newWord)) found = true;
                        if (wordSet.contains(newWord)) {
                            nextLevelVisited.add(newWord);
                            Node newNode = new Node(newWord);
                            cur.nextNodes.add(newNode);
                            queue.add(newNode);
                        }
                    }
                }
            }
            if (found) break;
            wordSet.removeAll(nextLevelVisited);
            nextLevelVisited.clear();
        }
        return found;
    }

    private void dfs(List<List<String>> res, Node cur, String endWord, Deque<String> stack) {
        if (cur.word.equals(endWord)) {
            res.add(new ArrayList<>(stack));
            return;
        }
        if (cur.nextNodes.size() == 0) return;
        for (Node next : cur.nextNodes) {
            stack.addLast(next.word);
            dfs(res, next, endWord, stack);
            stack.removeLast();
        }
    }

    class Node {
        String word;
        List<Node> nextNodes = new LinkedList<>();
        public Node(String word) {
            this.word = word;
        }
    }
}
// @lc code=end


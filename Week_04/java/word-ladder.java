/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (42.46%)
 * Likes:    375
 * Dislikes: 0
 * Total Accepted:    49.2K
 * Total Submissions: 115K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: 5
 * 
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * ⁠    返回它的长度 5。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: 0
 * 
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 */

// @lc code=start
class Solution {
    /**
     * BFS 单向搜索 （因为已知终点，所以可以进一步优化为双向搜索）
     * time -> O(m * n) m为word长度，n为wordList大小
     * space -> O(m * n) wordMap中需要为每个word存储m个pattern；BFS的队列中最坏的情况需要存储n个word
     * @author caohui
     * @date 2020/07/15
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) return 0;
        int l = beginWord.length();

        Map<String, List<String>> wordMap = new HashMap<>();
        for (String word : wordList) {
            IntStream.range(0, l)
                .mapToObj(i -> new StringBuilder(word).replace(i, i+1, "*").toString())
                .forEach(pattern -> {
                    wordMap.putIfAbsent(pattern, new LinkedList<>());
                    wordMap.get(pattern).add(word);
                });
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int level = 1;
        while (!queue.isEmpty()) {
            for (int n = queue.size(); n > 0; n--) {
                String word = queue.remove();
                if (visited.contains(word)) continue;
                visited.add(word);
                List<String> patterns = IntStream.range(0, l)
                        .mapToObj(i -> new StringBuilder(word).replace(i, i+1, "*").toString())
                        .collect(Collectors.toList());
                for (String p : patterns) {
                    if (wordMap.getOrDefault(p, new LinkedList<>()).contains(endWord)) return level + 1;
                    for (String w : wordMap.getOrDefault(p, new LinkedList<>())) {
                        queue.add(w);
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
// @lc code=end


/*
 * @lc app=leetcode.cn id=433 lang=java
 *
 * [433] 最小基因变化
 *
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/description/
 *
 * algorithms
 * Medium (50.53%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    6K
 * Total Submissions: 11.7K
 * Testcase Example:  '"AACCGGTT"\n"AACCGGTA"\n["AACCGGTA"]'
 *
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 
 * 现在给定3个参数 — start, end,
 * bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回
 * -1。
 * 
 * 注意:
 * 
 * 
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 * 
 * 
 * 示例 1:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 
 * 返回值: 1
 * 
 * 
 * 示例 2:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 
 * 返回值: 2
 * 
 * 
 * 示例 3:
 * 
 * 
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 
 * 返回值: 3
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * BFS 单向搜索
     * time -> O(m*n) m为字符串长度, n为bank数组大小
     * sapce -> O(n)
     * TODO 因为已知终点，可以进一步优化为双向搜索
     * @author caohui
     * @date 2020/07/16
     */
    public int minMutation(String start, String end, String[] bank) {
        if (bank.length == 0) return -1;
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;

        List<String> four = Arrays.asList("A", "C", "G", "T");
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int level = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                String s = queue.remove();
                if (visited.contains(s)) continue;
                visited.add(s);

                for (int j = 0; j < start.length(); j++) {
                    for (String c : four) {
                        String newStr = new StringBuilder(s).replace(j, j+1, c).toString();
                        if (newStr.equals(end)) return level + 1;
                        if (bankSet.contains(newStr)) {
                            queue.add(newStr);
                        }
                    }
                }
            }
            level++;
        }

        return -1;
    }
}
// @lc code=end


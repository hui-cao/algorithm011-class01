/*
 * @lc app=leetcode.cn id=541 lang=java
 *
 * [541] 反转字符串 II
 *
 * https://leetcode-cn.com/problems/reverse-string-ii/description/
 *
 * algorithms
 * Easy (54.52%)
 * Likes:    87
 * Dislikes: 0
 * Total Accepted:    19.6K
 * Total Submissions: 35.8K
 * Testcase Example:  '"abcdefg"\n2'
 *
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * 
 * 
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 该字符串只包含小写英文字母。
 * 给定字符串的长度和 k 在 [1, 10000] 范围内。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * time -> O(n)
     * space -> O(n)
     * @author caohui
     * @date 2020/08/22
     */
    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray();
        int n = s.length();
        for(int i = 0; i < n; i += 2 * k) {
           int j = Math.min(i + k - 1, n - 1);
           rev(chs, i, j);
        }
        return String.valueOf(chs);
    }

    private void rev(char[] chs, int i, int j) {
        while (i < j) {
            char temp = chs[i]; chs[i] = chs[j]; chs[j] = temp;
            i++;
            j--;
        }
    }
}
// @lc code=end


/*
 * @lc app=leetcode.cn id=242 lang=java
 *
 * [242] 有效的字母异位词
 *
 * https://leetcode-cn.com/problems/valid-anagram/description/
 *
 * algorithms
 * Easy (60.28%)
 * Likes:    206
 * Dislikes: 0
 * Total Accepted:    111.3K
 * Total Submissions: 184.4K
 * Testcase Example:  '"anagram"\n"nagaram"'
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 
 * 示例 1:
 * 
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * 
 * @author caohui
 * @date 2020/06/30
 */

// @lc code=start
class Solution {
    /**
     * 字母表实现哈希计数
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) if (--alphabet[t.charAt(i) - 'a'] < 0) return false;
        for (int i = 0; i < alphabet.length; i++) if (alphabet[i] != 0) return false;
        return true;
    }

    /**
     * 哈希表计数
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < s.length(); i++) m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0) + 1);
        for (int i = 0; i < t.length(); i++) {
            m.put(t.charAt(i), m.getOrDefault(t.charAt(i), 0) - 1);
            if (m.getOrDefault(t.charAt(i), 0) < 0) return false;
        }
        return ! m.values().stream().filter(i -> i != 0).findAny().isPresent();
    }

    /**
     * 排序比较
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sChars = s.toCharArray();//底层会通过 System.arraycopy 创建新数组
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }
}
// @lc code=end


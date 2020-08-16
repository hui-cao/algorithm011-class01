/*
 * @lc app=leetcode.cn id=231 lang=java
 *
 * [231] 2的幂
 *
 * https://leetcode-cn.com/problems/power-of-two/description/
 *
 * algorithms
 * Easy (48.40%)
 * Likes:    233
 * Dislikes: 0
 * Total Accepted:    73.5K
 * Total Submissions: 151.6K
 * Testcase Example:  '1'
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * 
 * 示例 1:
 * 
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 * 
 * 示例 2:
 * 
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 * 
 * 示例 3:
 * 
 * 输入: 218
 * 输出: false
 * 
 */

// @lc code=start
class Solution {
    /**
     * time -> O(1)
     * @author caohui
     * @date 2020/08/16
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    /**
     * time -> O(1)
     * @author caohui
     * @date 2020/08/16
     */
    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        long l = (long) n;
        return (l & (-l)) == l;
    }

    /**
     * time -> O(logn)
     * @author caohui
     * @date 2020/08/16
     */
    public boolean isPowerOfTwo1(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }
}
// @lc code=end


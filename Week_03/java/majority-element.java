/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 多数元素
 *
 * https://leetcode-cn.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (63.60%)
 * Likes:    658
 * Dislikes: 0
 * Total Accepted:    184.8K
 * Total Submissions: 289.7K
 * Testcase Example:  '[3,2,3]'
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: [3,2,3]
 * 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * 
 * @author caohui
 * @date 2020/07/09
 */

// @lc code=start
class Solution {
    /**
     * 排序
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 分治
     * 时间复杂度 O(nlogn) 通过主定理的递推表达式 T(n) = 2T(n/2) + 2n 可得
     * 空间复杂度 O(logn) 递归栈空间
     */
    public int majorityElement1(int[] nums) {
        return recursion(nums, 0, nums.length -1);
    }

    private int recursion(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];

        int mid = (hi - lo) / 2 + lo;
        int left = recursion(nums, lo, mid);
        int right = recursion(nums, mid + 1, hi);

        if (left == right) return left;

        return count(nums, lo, mid, left) > count(nums, mid + 1, hi, right) 
                    ? left
                    : right;
    }

    private int count(int[] nums, int lo, int hi, int num) {
        int count = 0;
        while (lo <= hi) if(nums[lo++] == num) count++;
        return count;
    }
}
// @lc code=end


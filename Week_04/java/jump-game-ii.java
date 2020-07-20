/*
 * @lc app=leetcode.cn id=45 lang=java
 *
 * [45] 跳跃游戏 II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (36.75%)
 * Likes:    632
 * Dislikes: 0
 * Total Accepted:    70.8K
 * Total Submissions: 191.6K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 
 * 
 * 说明:
 * 
 * 假设你总是可以到达数组的最后一个位置。
 * 
 */

// @lc code=start
class Solution {
    /**
     * greedy 正向查找
     * 子问题：1. 当前跳可达的最远位置为边界，找到边界内所有下一跳可达的最远位置；2；将此位置作为边界递推求解
     * time -> O(n)
     * space -> O(1)
     * @author caohui
     * @date 2020/07/19
     */
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int step = 0, end = nums[0], max = 0;
        for (int i = 0; i <= nums.length - 2; i++) {
            max = Math.max(i + nums[i], max);
            if (i == end) {
                end = max;
                step++;
            }
        }
        return step + 1;
    }

    /**
     * greedy 反向查找
     * 子问题：1. 找到能到达目标的最远的最后一跳位置；2. 将此位置作为目标递推求解
     * time -> O(n^2)
     * space -> O(1)
     * @author caohui
     * @date 2020/07/19
     */
    public int jump1(int[] nums) {
        int step = 0, target = nums.length -1;
        while (target > 0) {
            for (int i = 0; i < target; i++) {// 从左向右遍历以找到最大跨度
                if (i + nums[i] >= target) {
                    step++;
                    target = i;
                    break;
                }
            }
        }
        return step;
    }
}
// @lc code=end


/**
 * [剑指 Offer 11] 旋转数组的最小数字
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * @author caohui
 * @date 2020/07/22
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (numbers[mid] > numbers[right]) left = mid + 1;
            else if (numbers[mid] == numbers[right]) right--; // 存在数字重复的情况
            else right = mid;
        }
        return numbers[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

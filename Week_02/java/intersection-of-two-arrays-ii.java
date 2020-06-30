/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/description/
 *
 * algorithms
 * Easy (48.81%)
 * Likes:    293
 * Dislikes: 0
 * Total Accepted:    93.2K
 * Total Submissions: 190.5K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 
 * 示例 1:
 * 
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 
 * 说明：
 * 
 * 
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 
 * 
 * 进阶:
 * 
 * 
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * 
 * problem: https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * 进阶问题1：使用「排序双指针」的解法
 * 进阶问题2：当 n 足够小（如n=1），使得 O(n log m) < O(max(n,m)) 时，使用「二分查找」解法，否则使用「双指针」的解法。
 * 进阶问题3：1) 如果只有nums2无法容纳在内存中，请将nums1的所有元素放入HashMap中，读取适合内存的数组块，并记录交集。
 *          2) 如果nums1和nums2都太大而无法容纳到内存中，请分别对其进行排序（外部排序），然后一次从内存中的每个数组中读取2个元素，并记录交集。
 * @author caohui
 * @date 2020/06/30
 */

// @lc code=start
class Solution {

    /**
     * 二分查找
     * 遍历较小的数组，对其每个元素在较大的数组中进行二分查找。其中二分查找时需要考虑重复值。
     * 时间复杂度 O(n log m)，如果数组已序，n、m 分别为较短和较长数组的长度，即 n 次二分查找
     * 空间复杂度 O(1) 忽略了存放结果所使用的空间，其对算法本身不重要
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[0];
        if (nums1.length > nums2.length) return intersect(nums2, nums1);
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int shift = 0, k = 0;
        for (int i = 0; i < nums1.length; i++) {
            int j  = binarySearch(nums2, nums1[i], shift);
            if (j != -1) {
                nums1[k++] = nums1[i];
                shift = j + 1;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

    // Binary search from 'lo' to end of array.
    private int binarySearch(int[] sorted, int value, int lo) {
        int hi = sorted.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sorted[mid] < value) lo = mid + 1;
            else if (sorted[mid] > value) hi = mid - 1;
            else {
                // If duplicates exist, return the index for the match furthest left.
                hi = mid;
            }
        }

        return lo < sorted.length && sorted[lo] == value ? lo : -1;
    }

    /**
     * 排序双指针
     * 时间复杂度 O(max(n,m))，程序本身因为有排序，所以是O(nlogn)的，但如果认为数组是已序的，则变为O(max(n,m))。
                利用两个指针进行了一次线性扫描，最坏的情况，比如 nums1={100}, nums2={1, 2, ..., 100}，则需要完全遍历较长的数组。
     * 空间复杂度 O(1)，忽略了存放结果所使用的空间，其对算法本身不重要
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int k = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                nums1[k++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

    /**
     * 哈希计数
     * 时间复杂度 O(max(n,m))，对数组进行了两次遍历，n、m 为两个数组的长度
     * 空间复杂度 O(min(n,m))，对较小数组进行hash映射的空间
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersect1(nums2, nums1);
        Map<Integer, Integer> m = new HashMap<>();
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int n : nums2) {
            // 小数组所有元素计数归零的情况下尽早退出大数组的循环
            if (m.size() == 0) break;
            int count = m.getOrDefault(n, 0);
            if (count > 0) {
                nums1[k++] = n;
                m.put(n, count - 1);
            }
            else m.remove(n);
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}
// @lc code=end


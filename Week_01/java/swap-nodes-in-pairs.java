/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (65.89%)
 * Likes:    530
 * Dislikes: 0
 * Total Accepted:    115.8K
 * Total Submissions: 175.6K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 
 * 
 * 示例:
 * 
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 
 * problem: https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @author caohui
 * @date 2020/06/25
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * loop
     * O(n) / O(1)
     */ 
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode newHead = head.next, prev = dummy, second = null;

        while (head != null && head.next != null) {
            second = head.next;

            prev.next = second;
            head.next = second.next;
            second.next = head;            
            
            // for next swap
            prev = head;
            head = head.next;
        }

        return newHead;
    }

    /** 
     * recursion
     * O(n) / O(n)
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode second = head.next;
        head.next = swapPairs1(second.next);
        second.next = head;
        
        return second;
    }
}
// @lc code=end


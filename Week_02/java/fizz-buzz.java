/*
 * @lc app=leetcode.cn id=412 lang=java
 *
 * [412] Fizz Buzz
 *
 * https://leetcode-cn.com/problems/fizz-buzz/description/
 *
 * algorithms
 * Easy (63.77%)
 * Likes:    64
 * Dislikes: 0
 * Total Accepted:    37.4K
 * Total Submissions: 58.7K
 * Testcase Example:  '1'
 *
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * 
 * 示例：
 * 
 * n = 15,
 * 
 * 返回:
 * [
 * ⁠   "1",
 * ⁠   "2",
 * ⁠   "Fizz",
 * ⁠   "4",
 * ⁠   "Buzz",
 * ⁠   "Fizz",
 * ⁠   "7",
 * ⁠   "8",
 * ⁠   "Fizz",
 * ⁠   "Buzz",
 * ⁠   "11",
 * ⁠   "Fizz",
 * ⁠   "13",
 * ⁠   "14",
 * ⁠   "FizzBuzz"
 * ]
 * 
 * @author caohui
 * @date 2020/06/30
 */

// @lc code=start
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new LinkedList<>();
        for (int num = 1; num <= n; num++) {
            if (num % 3 == 0 && num % 5 == 0) res.add("FizzBuzz");
            else if (num % 3 == 0) res.add("Fizz");
            else if (num % 5 == 0) res.add("Buzz");
            else res.add(Integer.toString(num));
        }
        return res;
    }
    
    /**
     * 扩展性更好
     */
    public List<String> fizzBuzz1(int n) {
        List<String> result = new LinkedList<>();
        Map<Integer, String> map = new HashMap<>() {{
                put(3, "Fizz");
                put(5, "Buzz");
        }};
        
        for (int i = 1; i <= n; i++) {
            final int num = i;
            result.add(
                map.entrySet()
               .stream()
               .filter(e -> num % e.getKey() == 0)
               .map(e -> e.getValue())
               .reduce(String::concat)
               .orElse(Integer.toString(i))
            );
        }
        return result;
    }
}
// @lc code=end


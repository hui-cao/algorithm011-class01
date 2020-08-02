学习笔记 W06

# 12. 动态规划

[Dynamic programming - Wikipedia](https://en.wikipedia.org/wiki/Dynamic_programming)
> simplifying a complicated problem by breaking it down into simpler sub-problems in a  [recursive](https://en.wikipedia.org/wiki/Recursion)  manner.   
>   
> Likewise, in computer science, if a problem can be solved optimally by breaking it into sub-problems and then recursively finding the optimal solutions to the sub-problems, then it is said to have  [optimal substructure](https://en.wikipedia.org/wiki/Optimal_substructure) .  
>   
> 在计算机科学中，如果可以通过将问题分解为子问题然后递归地找到子问题的最优解来最佳地解决问题，则可以说它具有最优子结构。  

可以把动态规划理解为：分治 + 最优子结构。动态规划一般要解决的问题往往是求最优解、最大值或最少的方式等等。 因为有最优子结构的存在，在求解过程中的每一步不需要把所有的状态都存储下来，而是只需要存储最优的状态，但前提是能够证明：如果每一步都存最优的值，最后能够推导出一个全局最优的值，即存在最优子结构。

简单来说，动态规划需要有以下两步动作：
1. 引入缓存，即存储状态的数组；
2. 在每一步中需要淘汰次优的状态，只保留这一步里面的最优状态，以推导出最后的全局最优解。

动态规划和递归或分治没有根本上的区别，他们的共性是需要找到重复子问题，差异是动态规划有最优子结构，故中途可以淘汰次优解。如果不存在最优子结构，则说明所有的子问题都需要计算一遍，最后把结果合并在一起，也就是所谓的「分治」。也可以把「分治」理解为特殊的「动态规划」，即每次的最优解就是当前解，它没有每次比较和淘汰的过程。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ghcmu7t7gsj30k30bedpv.jpg)

解决 DP 问题的步骤：
1. 定义子问题
2. 定义状态数组
3. 递归公式（状态转移方程、DP方程）


## 参考链接
*  [递归代码模板](https://shimo.im/docs/EICAr9lRPUIPHxsH) 
*  [分治代码模板](https://shimo.im/docs/zvlDqLLMFvcAF79A) 
*  [动态规划定义](https://en.wikipedia.org/wiki/Dynamic_programming) 
*  [MIT 动态规划课程最短路径算法](https://www.bilibili.com/video/av53233912?from=search&seid=2847395688604491997) 

- - - -

[零钱兑换](https://leetcode-cn.com/problems/coin-change/)  问题

经典题解：[动态规划套路详解](https://leetcode-cn.com/problems/coin-change/solution/dong-tai-gui-hua-tao-lu-xiang-jie-by-wei-lai-bu-ke/)

与爬楼梯问题相似：硬币种类 -> 每次上几级台阶

1. 暴力递归： `O(S^n)` s 为金额；n为硬币种类数

子问题的递归状态树
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ghcmuplojsj31jk0nm0xx.jpg)
2. BFS：`0`所在的层数即硬币数
3. DP：
	1. 子问题 `problem(amount) =  Min( problem(amount-1)+1 | problem(amount-2)+1 | problem(amount-5)+1 )`
	2. DP数组 `f[a] -> 硬币个数, a -> 总金额`
	3. DP方程 `f(a) = Min([ f(a-k) + 1 ]), k in [1,2,5]`

- - - -

## 实战题目
- [x]  [最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/) （字节跳动、谷歌、亚马逊在半年内面试中考过）
	* [动态规划之最长公共子序列（LCS）](https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/)
- [ ]  [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/description/) （阿里巴巴、腾讯、字节跳动在半年内面试常考）
	1. 每次可以上 1、2、3阶（easy）
	2. 相邻两步的步伐不能相同（medium）
- [x]  [三角形最小路径和](https://leetcode-cn.com/problems/triangle/description/) （亚马逊、苹果、字节跳动在半年内面试考过）
	* 三角形最小路径和高票回答：[Triangle - LeetCode](https://leetcode.com/problems/triangle/discuss/38735/Python-easy-to-understand-solutions-(top-down-bottom-up) )
*  [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) （亚马逊、字节跳动在半年内面试常考）
	1. 暴力：`n^2`
	2. DP：
		1. 分治（子问题）：`max_sum(i) = Max(max_sum(i-1), 0) + a[i]`
		2. 状态数组定义： `f[i]`
		3. DP方程： `f[i] = Max(f[i-1], 0) + a[i]`
*  [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/description/) （亚马逊、字节跳动、谷歌在半年内面试中考过）
*  [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/) （亚马逊在半年内面试中常考）
	*  [动态规划套路详解-力扣](https://leetcode-cn.com/problems/coin-change/solution/dong-tai-gui-hua-tao-lu-xiang-jie-by-wei-lai-bu-ke/)




## 实战题目
- [x]  [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/) （字节跳动、谷歌、亚马逊在半年内面试中考过）
*  [打家劫舍 II ](https://leetcode-cn.com/problems/house-robber-ii/description/) （字节跳动在半年内面试中考过）
*  [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/#/description) （亚马逊、字节跳动、Facebook 在半年内面试中常考）
*  [买卖股票的最佳时机 II ](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) （亚马逊、字节跳动、微软在半年内面试中考过）
*  [买卖股票的最佳时机 III ](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) （字节跳动在半年内面试中考过）
*  [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) （谷歌、亚马逊在半年内面试中考过）
*  [买卖股票的最佳时机 IV ](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) （谷歌、亚马逊、字节跳动在半年内面试中考过）
*  [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) 
*  [一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/) 
## 高级 DP 实战题目
*  [完全平方数](https://leetcode-cn.com/problems/perfect-squares/) （亚马逊、谷歌在半年内面试中考过）
*  [编辑距离](https://leetcode-cn.com/problems/edit-distance/)  **（重点）**
*  [跳跃游戏](https://leetcode-cn.com/problems/jump-game/) （亚马逊在半年内面试中考过）
*  [跳跃游戏 II ](https://leetcode-cn.com/problems/jump-game-ii/) （亚马逊、华为字节跳动在半年内面试中考过）
- [x]  [不同路径](https://leetcode-cn.com/problems/unique-paths/) （Facebook、亚马逊、微软在半年内面试中考过）
- [x]  [不同路径 II ](https://leetcode-cn.com/problems/unique-paths-ii/) （谷歌、美团、微软在半年内面试中考过）
*  [不同路径 III ](https://leetcode-cn.com/problems/unique-paths-iii/) （谷歌在半年内面试中考过）
- [x]  [零钱兑换](https://leetcode-cn.com/problems/coin-change/) （亚马逊在半年内面试中常考）
*  [零钱兑换 II ](https://leetcode-cn.com/problems/coin-change-2/) （亚马逊、字节跳动在半年内面试中考过）

- - - -
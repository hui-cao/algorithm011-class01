# 学习笔记 W4
---



# DFS & BFS

## 遍历搜索
在树（图/状态集）中寻找特定结点

* 每个节点都要访问一次
* 每个节点仅仅要访问一次
* 对于节点的访问顺序不限
	* 深度优先：depth first search
	* 广度优先：breadth first search

## DFS

### 遍历顺序

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvkx0w9bsj30hs09qgmn.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvkxma399j30ct09vt9b.jpg)

递归写法代码模板

```python
visited = set() 

def dfs(node, visited):
	if node in visited: # terminator
		# already visited 
		return 
	visited.add(node) 
	# process current node here. 
	# ...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```

非递归写法代码模板

```python
def dfs(self, tree): 
	if tree.root is None: 
		return [] 
	visited, stack = [], [tree.root]
	while stack: 
		node = stack.pop() 
		visited.add(node)

		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 
	# other processing work 
	# ...
```

## BFS 

### 遍历顺序

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvky1p9doj30cz09t0th.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvky9zbsej30jd074aau.jpg)


代码模板

```python
def bfs(graph, start, end):
    # init
    visited = set() # 用于图中记录已访问的元素；对于树则不需要
    queue = [] 
    queue.append([start]) 
    while queue: 
        node = queue.pop() 
        if node in visited: #对于图的话，需要进行判断；对于树可以省略
            visited.add(node)
        
        process(node) 
        nodes = generate_related_nodes(node)
        queue.push(nodes)
    # other processing work 
    # ...
```

使用**队列**，把每个还没有搜索到的点依次放入队列，然后再弹出队列的头部元素当做当前遍历点。

按是否记录层级，BFS可以进一步两个模板：

1. 如果不需要确定当前遍历到了哪一层，BFS模板如下。

```python
while queue 不空 :
    cur = queue.pop()
    for 节点 in cur的所有相邻节点 :
        if 该节点有效且未访问过 :
            queue.push(该节点)
```

2. 如果要确定当前遍历到了哪一层，BFS模板如下。
这里增加了level表示当前遍历到二叉树中的哪一层了，也可以理解为在一个图中，现在已经走了多少步了。size表示在当前遍历层有多少个元素，也就是队列中的元素数，我们把这些元素一次性遍历完，即把当前层的所有元素都向外走了一步。

```python
level = 0
while queue 不空 : 
    size = queue.size()
    while (size --) {
        cur = queue.pop()
        for 节点 in cur的所有相邻节点：
            if 该节点有效且未被访问过：
                queue.push(该节点)
    }
    level ++;
```

```java
// Java
public List<List<Integer>> bfs(TreeNode root) {
    List<List<Integer>> res = new LinkedList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    
    while (!queue.isEmpty()) {
        List<Integer> curLevel = new LinkedList<>();
        for (int i = queue.size(); i > 0; i--) {
            TreeNode cur = queue.remove();
            curLevel.add(cur.val);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        res.add(curLevel);
    }

    return res;
}
```

应用：
* 层次遍历
* 最短路径
`Dijkstra`算法解决的是**带权最短路径**问题，而我们这里关注的是**无权最短路径**问题，也可以看成每条边的权重都是 1。这样的最短路径问题，用 BFS 求解就行了。


## 相关题目

- [x] [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)  （字节跳动、亚马逊、微软在半年内面试中考过）
- [x]  [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) 
- [x]  [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/) （字节跳动、亚马逊、Facebook 在半年内面试中考过）
- [x]  [515. 在每个树行中找最大值](https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/) （微软、亚马逊、Facebook 在半年内面试中考过）

- [x] [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) （亚马逊在半年内面试常考）
- [x] [126. 单词接龙 II](https://leetcode-cn.com/problems/word-ladder-ii/) （微软、亚马逊、Facebook 在半年内面试中考过）
- [ ] [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) （近半年内，亚马逊在面试中考查此题达到 350 次）
- [ ]  [529. 扫雷游戏](https://leetcode-cn.com/problems/minesweeper/) （亚马逊、Facebook 在半年内面试中考过）



# 贪心算法 Greedy


**贪心算法**是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。 

**贪心算法**与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。**动态规划**则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

贪心算法可以解决一些最优化问题，如：求图中的最小生成树、求哈夫曼编码等。然而对于工程和生活中的问题，贪心法一般不能得到我们所要求的答案。 

一旦一个问题可以通过贪心算法来解决，那么贪心算法一般是解决这个问题的最好办法。由于贪心算法的高效性以及其所求得的答案比较接近最优结果，贪心算法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。

## 举个栗子

以  [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)  为例，增加一个限定条件，当硬币可选集合固定：Coins = [20, 10, 5, 1] ，求最少可以几个硬币拼出固定总金额。 比如 total = 36。

因为硬币的面额是倍数的关系，所以可以使用贪心算法。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvl56guh8j30bd077q3c.jpg)

## 贪心算法的反例

上述问题改变限定条件：非整除关系的硬币，可选集合：Coins = [10, 9, 1] ，求拼出总数为 18 最少需要几个硬币？

✅
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvl5cabq5j307y041dfv.jpg)

❌
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggvl5ka2kyj30j808j74t.jpg)

## 适用贪心算法的场景

简单地说，问题能够分解成子问题来解决，**子问题的最优解能递推到最终问题的最优解**。这种子问题最优解称为最优子结构。
贪心算法的具体代码往往比较简单，但难点在于证明问题可以使用贪心算法以及找到切入点。

## 参考链接

*  [动态规划定义](https://zh.wikipedia.org/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92) 

## 相关题目

- [ ] [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/) 
- [ ] [455. 分发饼干](https://leetcode-cn.com/problems/assign-cookies/) （亚马逊在半年内面试中考过）
- [ ] [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) （亚马逊、字节跳动、微软在半年内面试中考过）
- [ ] [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/) （亚马逊、华为、Facebook 在半年内面试中考过）
- [ ] [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) （亚马逊、华为、字节跳动在半年内面试中考过）
- [ ] [860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/) （亚马逊在半年内面试中考过）
- [ ] [874. 模拟行走机器人](https://leetcode-cn.com/problems/walking-robot-simulation/) 



# 二分查找

可以应用**二分查找**的前提：

1.  目标函数单调性（单调递增或者递减） 
2.  存在上下界（bounded） 
3.  能够通过索引访问（index accessible)

```python
# Python
left, right = 0, len(array) - 1 
while left <= right: 
	  mid = (left + right) / 2 
	  if array[mid] == target: 
		    # find the target!! 
		    break or return result 
	  elif array[mid] < target: 
		    left = mid + 1 
	  else: 
		    right = mid - 1
```
```java
// Java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```


## 参考链接

*  [Fast InvSqrt() 扩展阅读](https://www.beyond3d.com/content/articles/8/) 

## 相关题目

- [ ]  [x 的平方根](https://leetcode-cn.com/problems/sqrtx/) （字节跳动、微软、亚马逊在半年内面试中考过）
	1. 二分查找 
	2. 牛顿迭代法
- [ ]  [有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/) （亚马逊在半年内面试中考过）

- [ ]  [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) （Facebook、字节跳动、亚马逊在半年内面试常考）
	1. 暴力` O(n)`，查找中间无序的位置时，也可以使用二分查找，将`O(n)`优化为`O(logn)`
	2. 二分查找
- [ ]  [搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/) （亚马逊、微软、Facebook 在半年内面试中考过）
- [ ]  [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) （亚马逊、微软、字节跳动在半年内面试中考过）

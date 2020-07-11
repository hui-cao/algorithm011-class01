# 学习笔记 W3

回溯

| # | Title | Solution | Difficulty |
|---| ----- | -------- | ---------- |
|22|[括号生成](https://leetcode-cn.com/problems/generate-parentheses/) | [Java](./java/generate-parentheses.java)|Medium|

# 泛型递归、树的递归

**递归的代码模板**

```java
// Java
public void recursion(int level, int ... param) {
    // 1. terminator 递归的终止条件
    if (level > MAX_LEVEL) {
        // process result 
        return;
    }
    // 2. process current logic 处理当前层逻辑
    process(level, param);
    // 3. drill down 下探到下一层
    recursion(level + 1, param);
    // 4. reverse the current level status if needed 清理当前层的状态（如果第2步改变了当前层的状态）
    // ...
}
```

```python
# Python
def recursion(level, param1, param2, ...):
    # recursion terminator 
    if level > MAX_LEVEL: 
        process_result 
        return 
    # process logic in current level 
    process(level, data...) 
    # drill down 
    self.recursion(level + 1, p1, ...) 
    # reverse the current level status if needed
    # ...
```

**思维要点**

1. 不要人肉进行递归（最大误区） 
2. 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
3. 数学归纳法思维

## Leet Code

1.  [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) 

```
# 1. 1
# 2. 2
# 3. f(1) + f(2) mutual exclusive, complete exhaustive
# 4. f(2) + f(3)

# f(n) = f(n-1) + f(n-2) -> Fibonacci
# 1. 未缓存的递归
# 2. 动态规划 -> 循环
# 3. 通项公式
# 4. 矩阵乘法
```

[如何优雅地计算斐波那契数列？-极客时间](https://time.geekbang.org/dailylesson/detail/100028406)

2.  [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/) 

	1. 「(」 随时可以添加，但不能超过 n
	2. 「)」添加时必须满足 左个数 > 右个数


# 分治、回溯

分治和回溯的本质是递归。

## 分治 Divide & Conquer
> 在分治的方法中，将待解决的问题切分为较小的子问题，然后分别解决每个子问题。当我们继续将子问题划分为更小的子问题时，我们最终可能会达到无法再进行切分的阶段，而这些“原子的”最小可能子问题是已经被解决了的。最后将所有子问题的解进行合并，最终获得原始问题的解。  

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggmy4g07czj30fa09bdg9.jpg)
图片来源 [Data Structures - Divide and Conquer - Tutorialspoint](https://www.tutorialspoint.com/data_structures_algorithms/divide_and_conquer.htm)

**递归状态树**
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggmy5psv0vj30vy0dp0vj.jpg)
图片来源 [Divide and Conquer Algorithms. Divide-and-Conquer Algorithm | by James Le | Cracking The Data Science Interview | Medium](https://medium.com/cracking-the-data-science-interview/divide-and-conquer-algorithms-b135681d08fc)

分治的代码模板
```python
# Python
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
    print_result 
    return 

  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # process and generate the final result 合并结果
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```

常见的应用场景：

1. 二分查找 **Binary Search**
2. 快速排序 **Quicksort**
3. 合并排序 **Merge Sort**
4. 最接近的点对 **Closest Pair of Points**：问题是要在x-y平面的一组点中找到最接近的一对点。通过计算每对点的距离并比较距离以找出最小值，可以在O（n²）时间内解决该问题。分治算法解决了`O(nlogn)`时间的问题。
5. Strassen’s Matrix Multiplication
6. Map-Reduce 并行运算

## 回溯 Backtracking

**回溯**是一种算法思想，它增量地去尝试构建问题的解，每次尝试一个可能的解并及时移除不满足问题约束的解。

与**回溯**相对的是**穷举搜索（Exhaustive Search）**，其首先列出所有可能的解，然后从中选择最可行的解。由于它遵循的是最幼稚的方法，因此又称为**蛮力搜索**。

```
// 蛮力搜索的伪码例子
solve(parameters) {
    generate all possible solutions
    ans = select most feasible solution
    return ans
}
// 回溯的伪码例子
solve(parameters) {
    if ( solution works ) return solution
    else {
        // Backtrack, discard current solution
        return solve(modified_parameters) // generate next solution
    }
}

https://afteracademy.com/blog/what-is-backtrac
```

> Backtracking is an algorithmic-technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point of time (by time, here, is referred to the time elapsed till reaching any level of the search tree).  
>
> There are three types of problems in backtracking –  
> 1 Decision Problem – In this, we search for a feasible solution.  
> 2 Optimization Problem – In this, we search for the best solution.  
> 3 Enumeration Problem – In this, we find all feasible solutions.  
>
> [Backtracking | Introduction - GeeksforGeeks](https://www.geeksforgeeks.org/backtracking-introduction/)  
>
> For example, consider the SudoKo solving Problem, we try filling digits one by one. Whenever we find that current digit cannot lead to a solution, we remove it (backtrack) and try next digit. This is better than naive approach (generating all possible combinations of digits and then trying every combination one by one) as it drops a set of permutations whenever it backtracks.  
>
> [Backtracking Algorithms - GeeksforGeeks](https://www.geeksforgeeks.org/backtracking-algorithms/)  

**回溯**的代码模板即**递归**的代码模板

通过  [46. 全排列](https://leetcode-cn.com/problems/permutations/)  问题来理解**回溯**

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggmy6aqvwaj31kl0u04fu.jpg)

> “**回溯**”算法也叫“**回溯搜索**”算法，主要用于在一个庞大的空间里搜索我们所需要的问题的解。（用一份状态变量去遍历整个状态空间）  
>
> “**回溯**”指的是“**状态重置**”，可以理解为“回到过去”、“恢复现场”，是在编码的过程中，是为了节约空间而使用的一种技巧。而回溯其实是“**深度优先遍历**”特有的一种现象。之所以是“深度优先遍历”，是因为我们要解决的问题通常是在一棵树上完成的，在这棵树上搜索需要的答案，一般使用深度优先遍历。  
>
> 1、每一个结点表示了“全排列”问题求解的不同阶段，这些阶段通过变量的“不同的值”体现；  
> 2、这些变量的不同的值，也称之为“状态”；  
> 3、使用深度优先遍历有“回头”的过程，在“回头”以后，状态变量需要设置成为和先前一样；  
> 4、因此在回到上一层结点的过程中，需要撤销上一次选择，这个操作也称之为“状态重置”；  
> 5、深度优先遍历，可以直接借助系统栈空间，为我们保存所需要的状态变量，在编码中只需要注意遍历到相应的结点的时候，状态变量的值是正确的，具体的做法是：往下走一层的时候，path 变量在尾部追加，而往回走的时候，需要撤销上一次的选择，也是在尾部操作，因此 path 变量是一个栈。  
> 6、深度优先遍历通过“**回溯**”操作，实现了全局使用一份状态变量的效果。  
>
> 作者：liweiwei1419  
> 链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/  

**回溯**的图例

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggmy6nlxbfj31ft0u043j.jpg)
图片来源 [Backtracking](https://osiris.ubishops.ca/xxu/algorithms/backtrack.html)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggmy6vtuj5j30lf0l4ta2.jpg)
图片来源 [(PDF) White-Box Prediction of Process Performance Indicators via Flow Analysis](https://www.researchgate.net/publication/316610194_White-Box_Prediction_of_Process_Performance_Indicators_via_Flow_Analysis)
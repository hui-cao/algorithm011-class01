# 学习笔记 W1



## mindmap

![big-o-cheatsheet](assets/algorithms-summary.png)

## 算法分析

需要通过数学模型来描述：随着**问题规模**的增长，算法的运行成本（时间与空间）的**增长数据量级别**。

Θ 记号渐进的给出一个函数的上界和下界。 Ο 记号给出函数的**渐进上界**，而 Ω 记号给出函数的**渐进下界**。

![big-o-cheatsheet](assets/big-o-cheatsheet-3262782.png)

- O(1):  Constant Complexity 常数复杂度
- O(log n):  Logarithmic Complexity 对数复杂度
- O(n):  Linear Complexity 线性时间复杂度
- O(n^2): N square Complexity 平方
- O(n^3): N cubic Complexity 立方
- O(2^n): Exponential Growth 指数。**递归**操作的时间复杂度为 `O(k^n)`。
- O(n!): Factorial 阶乘

![image-20200628164703983](assets/image-20200628164703983.png)

## 数据结构

数据结构是指数据的组织和操作方式，其目的是找到使数据访问或操作更有效的方法。

###  数组 Array

数据是基于索引的数据结构，每个元素都有索引所引用。

数组的随机访问操作的时间复杂度是` O(1) `，插入、删除操作均为`O(n)`。

![image-20200628133754299](assets/image-20200628133754299.png)

### 链表 Linked List

链表是一系列节点，其中每个节点都连接到其后的节点，形成了一个链式的存储结构。它的每个节点由数据元素和指向下一个节点的引用组成，此时为**单向链表**。如果每个节点同时还包含了指向前一个节点的引用，则称为**双向链表**。如果尾节点的后继节点引用指向了头节点，则称为**循环链表**。

链表的插入、删除操作的时间复杂度为`O(1)`，随机访问、搜索操作均为`O(n)`。



![image-20200628134214980](assets/image-20200628134214980.png)

### 跳表 Skip List

跳表通过为原始链表建立多级索引的方式（设计思想：**升维 + 空间换时间**），以加速查询，其前提是链表的元素必须有序。其插入、删除、搜索的时间复杂度均为`O(logn)`，可用于替换平衡树（AVL Tree）和二分查找。

![image-20200628140801493](assets/image-20200628140801493.png)



假如每2个节点抽取一个索引节点，则第k级索引的节点个数则为`n/2^k`，索引的高度（级）则为`log2(n)-1`，每层索引需要遍历的节点个数为3个，故查询任意数据的时间复杂度为`O(logn)`。

![image-20200628141716212](assets/image-20200628141716212.png)

对于插入、删除操作，由于需要更新索引，维护成本较高，其时间复杂度由普通链表的`O(1)`降为`O(logn)`。



### 栈 Stack

栈是后进先出（LIFO）的数据结构，只能操作顶部的元素。通过`push`向栈的顶部压入（添加）元素，通过`pop`从栈的顶部弹出（移除）元素。添加、删除的时间复杂度均为`O(1)`。

![image-20200628162102096](assets/image-20200628162102096.png)

#### ` Java`实现

* `java.util.Stack` 通过继承 `Vector`实现，引入较早（JDK1.0），不推荐使用。`Deque`接口及其实现提供了一个更完整和连贯的`LIFO`栈操作，应优先使用`Deque`。
* `java.util.Deque`
  * `java.util.ArrayDeque`
  * `java.util.LinkedList`
  * `java.util.concurrent.BlockingDeque`
  * `java.util.concurrent.ConcurrentLinkedDeque`
  * `java.util.concurrent.LinkedBlockingDeque`

![image-20200628203309082](assets/image-20200628203309082.png)



### 队列 Queue

队列是先进先出（FIFO）的数据结构，从一端插入新元素，从另一端移除现有元素。添加、删除的时间复杂度均为`O(1)`。

![image-20200628154920811](assets/image-20200628154920811.png)

#### Java 实现

![image-20200628204413102](assets/image-20200628204413102.png)

* API 操作

![image-20200628204344504](assets/image-20200628204344504.png)

#### 双端队列 Deque（Double-End Queue）

Deque 是一个两端都可以进出的 Queue，可以通过组合不同的操作来很容易地实现 Stack 和 Queue。添加、删除的时间复杂度均为`O(1)`。

![image-20200628162315655](assets/image-20200628162315655.png)

* Java 实现

  ![image-20200628204821689](assets/image-20200628204821689.png)

* API操作

  ![image-20200628205250572](assets/image-20200628205250572.png)

* `java.util.LinkedList`

  ![image-20200628203453417](assets/image-20200628203453417.png)

#### 优先队列 Priority Queue

优先队列是 Queue 的一种实现，每个元素都有与之关联的优先级，按元素的优先级顺序进行出队操作。添加的时间复杂度为`O(1)`，删除的时间复杂度为`O(logn)`。

底层有多种实现方式：heap、bst、treap。

Java 实现：

* `java.util.PriorityQueue`

  ![image-20200628210837560](assets/image-20200628210837560.png)

## LeetCode


| # | Title | Solution | Difficulty |
|---| ----- | -------- | ---------- |
|283|[移动零](https://leetcode-cn.com/problems/move-zeroes/), [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | [Java](./java/move-zeroes.java)|Easy|
|206|[反转链表](https://leetcode-cn.com/problems/reverse-linked-list/), [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | [Java](./java/reverse-linked-list.java)|Medium|
|189|[旋转数组](https://leetcode-cn.com/problems/rotate-array/), [Rotate Array](https://leetcode.com/problems/rotate-array/) | [Java](./java/rotate-array.java)|Easy|
|70|[爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/), [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) | [Java](./java/climbing-stairs.java)|Easy|
|66|[加一](https://leetcode-cn.com/problems/plus-one/), [Plus One](https://leetcode.com/problems/plus-one/) | [Java](./java/plus-one.java)|Easy|
|26|[删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/), [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | [Java](./java/remove-duplicates-from-sorted-array.java)|Easy|
|24|[两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/), [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) | [Java](./java/swap-nodes-in-pairs.java)|Medium|
|15|[三数之和](https://leetcode-cn.com/problems/3-sum/), [3 Sum](https://leetcode.com/problems/3-sum/) | [Java](./java/3-sum.java)|Medium|
|11|[盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/), [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | [Java](./java/container-with-most-water.java)|Medium|
|1|[两数之和](https://leetcode-cn.com/problems/two-sum/), [Two Sum](https://leetcode.com/problems/two-sum/) | [Java](./java/two-sum.java)|Easy|



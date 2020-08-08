# 学习笔记 W07

# 13. 字典树

Trie (发音为 “try”) 或**前缀树**（又称单词查找树或键树）是一种树数据结构，典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。 
它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。

> Trie这个术语来自于re**trie**val。根据词源学 ，trie的发明者Edward Fredkin把它读作 `/ˈtriː/`  “tree”。但是，其他作者把它读作 `/ˈtraɪ/`  “try”。  
> [Trie - 维基百科](https://zh.wikipedia.org/wiki/Trie)  

## 基本性质
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjk6urb7pj30dq0cy3zv.jpg)
1. 结点本身不存完整单词； 
2. 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串； 
3. 每个结点的所有子结点路径代表的字符都不相同；
4. 结点可以存储额外信息，如词频。

> 还有其他的数据结构，如平衡树和哈希表，使我们能够在字符串数据集中搜索单词。为什么我们还需要 Trie 树呢？尽管哈希表可以在`O(1)`时间内寻找键值，却无法高效的完成以下操作：  
> 1. 找到具有同一前缀的全部键值。  
> 2. 按词典序枚举字符串的数据集。  
> Trie 树优于哈希表的另一个理由是，随着哈希表大小增加，会出现大量的冲突，时间复杂度可能增加到`O(n)`，其中 n 是插入的键的数量。与哈希表相比，Trie 树在存储多个具有相同前缀的键时可以使用较少的空间。此时 Trie 树只需要 `O(m) `的时间复杂度，其中 m 为键长。而在平衡树中查找键值需要 `O(mlogn)` 时间复杂度。  
> [实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/)  

## trie的实现
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjk797wnsj30hj098aaz.jpg)

**trie**为多叉树结构。如仅考虑`a-z`字母，则为一个26叉树；仅考虑`ASCII`字符，则为255叉树。当然也可以动态的增减树的分支。

Trie 树的核心思想是空间换时间。利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。

## 参考链接
* [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/) 
* [Tire 树代码模板](https://shimo.im/docs/DP53Y6rOwN8MTCQH) 
* [二叉树的层次遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) 

## 实战题目
- [ ]  [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/#/description) （亚马逊、微软、谷歌在半年内面试中考过）
*  [单词搜索 II ](https://leetcode-cn.com/problems/word-search-ii/) （亚马逊、微软、苹果在半年内面试中考过）
* 分析单词搜索 2 用 Tire 树方式实现的时间复杂度，请同学们提交在学习总结中。

- - - -

# 13. 并查集 Disjoint Set

Union-Find代码模板
```java
class UnionFind {
    private int count = 0;
    private int[] parent;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        IntStream.range(0, n).forEach(i -> parent[i] = i);
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // 压缩
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
}
```

[朋友圈](https://leetcode-cn.com/problems/friend-circles) 
1. DFS | BFS：题中的**矩阵M**即为 **图**的**邻接矩阵**表示。
2. Union-Find

## 参考链接
*  [并查集代码模板](https://shimo.im/docs/VtcxL0kyp04OBHak) 
## 实战题目
- [x]  [朋友圈](https://leetcode-cn.com/problems/friend-circles) （亚马逊、Facebook、字节跳动在半年内面试中考过）
*  [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/) （近半年内，亚马逊在面试中考查此题达到 361 次）
*  [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/) （亚马逊、eBay、谷歌在半年内面试中考过）

- - - -

# 14. 高级搜索

## 剪枝的实现和特性

### 参考链接
*  [DFS 代码模板](https://shimo.im/docs/UdY2UUKtliYXmk8t) 
*  [BFS 代码模板](https://shimo.im/docs/ZBghMEZWix0Lc2jQ) 
*  [AlphaZero Explained](https://nikcheerla.github.io/deeplearningschool/2018/01/01/AlphaZero-Explained/) 
*  [棋类复杂度](https://en.wikipedia.org/wiki/Game_complexity) 

### 实战题目
- [x] [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) （阿里巴巴、腾讯、字节跳动在半年内面试常考）
- [x] [括号生成](https://leetcode-cn.com/problems/generate-parentheses/) （亚马逊、Facebook、字节跳动在半年内面试中考过）
- [x] [N 皇后](https://leetcode-cn.com/problems/n-queens/) （亚马逊、苹果、字节跳动在半年内面试中考过）
- [x] [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/description/) （亚马逊、苹果、微软在半年内面试中考过）
- [ ] [解数独](https://leetcode-cn.com/problems/sudoku-solver/#/description) （亚马逊、华为、微软在半年内面试中考过）

## 双向BFS

### 实战题目
- [x] [单词接龙](https://leetcode-cn.com/problems/word-ladder/) （亚马逊、Facebook、谷歌在半年内面试中考过）
- [x] [最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) （谷歌、Twitter、腾讯在半年内面试中考过）

- [x] 总结双向 BFS 代码模版
```java
public void debfs(Node start, Node end) {
    Set<Node> visited = new HashSet<>();

    Queue<Node> startQ = new LinkedList<>();
    Queue<Node> endQ = new LinkedList<>();
    startQ.add(start);
    endQ.add(end);

    while (!startQ.isEmpty()) {
        Queue<Node> tmp;
        if (startQ.size() > endQ.size()) {
            tmp = startQ;
            startQ = endQ;
            endQ = tmp;
        }
        tmp = new LinkedList<>();
        for (Node cur : startQ) {
            visited.add(cur);
            // process node
            for (Node next : generateRelatedNodes()) {
                if (!visited.contains(next)) tmp.add(next);
            }
        }
        startQ = tmp;
    }

    return;
}
```

## 启发式搜索 Heuristic Search (A*)

### 参考链接
*  [A* 代码模板](https://shimo.im/docs/8CzMlrcvbWwFXA8r) 
*  [相似度测量方法](https://dataaspirant.com/2015/04/11/five-most-popular-similarity-measures-implementation-in-python/) 
*  [二进制矩阵中的最短路径的 A* 解法](https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/313347/A*-search-in-Python) 
*  [8 puzzles 解法比较](https://zxi.mytechroad.com/blog/searching/8-puzzles-bidirectional-astar-vs-bidirectional-bfs/) 

### 实战题目
- [ ] [二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/) （亚马逊、字节跳动、Facebook 在半年内面试中考过）
- [ ] [滑动谜题](https://leetcode-cn.com/problems/sliding-puzzle/) （微软、谷歌、Facebook 在半年内面试中考过）
- [ ] [解数独](https://leetcode-cn.com/problems/sudoku-solver/) （微软、华为、亚马逊在半年内面试中考过）

- - - -

# 15. AVL树和红黑树

树的性能与其**高度**相关，保证其性能的关键点是保证其子树之间**高度的平衡**。树在不平衡的极端情况下会退化成一维的**链表**结构，即从`O(logn)`退化为`O(n)`。

[Self-balancing binary search tree - Wikipedia](https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree)
> Data structures implementing this type of tree include:  
>  [2–3 tree](https://en.wikipedia.org/wiki/2%E2%80%933_tree) *  
>  [AA tree](https://en.wikipedia.org/wiki/AA_tree)   
>  [AVL tree](https://en.wikipedia.org/wiki/AVL_tree) *  
>  [B-tree](https://en.wikipedia.org/wiki/B-tree) *  
>  [Red–black tree](https://en.wikipedia.org/wiki/Red%E2%80%93black_tree) *  
>  [Scapegoat tree](https://en.wikipedia.org/wiki/Scapegoat_tree)   
>  [Splay tree](https://en.wikipedia.org/wiki/Splay_tree) #  
>  [Treap](https://en.wikipedia.org/wiki/Treap)  #  
>  [Weight-balanced tree](https://en.wikipedia.org/wiki/Weight-balanced_tree)   

## AVL树

> Named after inventors **A**delson-**V**elsky and **L**andis  
> [AVL tree - Wikipedia](https://en.wikipedia.org/wiki/AVL_tree)  

1. AVL是一种平衡二叉搜索树（self-balancing binary search tree）
2. 每个节点需要保存平衡因子（balace factor），其值为该节点的左、右子树的高度相减。要求 `balace factor = {-1, 0, 1}`
3. 当平衡因子不满足要求时，需要通过四种旋转操作来进行平衡

 [Data Structure and Algorithms - AVL Trees - Tutorialspoint](https://www.tutorialspoint.com/data_structures_algorithms/avl_tree_algorithm.htm)
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkag5am5j30dw05gmxb.jpg)

为了平衡自身，AVL树需要执行以下四种旋转：
* Left rotation (Right-Right Case)
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkb0jycnj30dw0550sx.jpg)

* Right rotation (Left-Left Case)
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkb7v8byj30dw0510sw.jpg)

* Left-Right rotation (Left-Right Case)
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkbg3js1j30qi04740a.jpg)

* Right-Left rotation (Right-Left Case)
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkbm1bm1j30r204qq4w.jpg)

带有子树的旋转：
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkbs10udg306y06ywlt.gif)

[File:Tree Rebalancing.gif - Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Tree_Rebalancing.gif)
![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkc0yb85g316f0u0acm.gif)

示例：[AVL Tree | Set 1 (Insertion) - GeeksforGeeks](https://www.geeksforgeeks.org/avl-tree-set-1-insertion/)

不足：
1. 节点需要存储额外的信息（int类型的平衡因子）
2. 因为严格要求平衡，调整频繁
- - - -

## 红黑树

红黑树是一种近似平衡的二叉搜索树（Binary Search Tree），它能够确保任何一个结点的左右子树的高度差小于两倍。具体来说，红黑树是满足如下条件的二叉搜索树：

* 每个结点要么是红色，要么是黑色
* 根结点是黑色
* 每个叶结点（NIL结点、空结点）是黑色的
• 不能有相邻接的两个红色结点
• 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点

![](https://tva1.sinaimg.cn/large/007S8ZIlly1ghjkcg0cvpj30yq0ismxt.jpg)

关键性质：从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。

AVL tree VS. Red-Black tree:
* AVL trees providefaster lookupsthan Red Black Trees because they are more strictly balanced.
• Red Black Trees providefaster insertion and removal operations than AVL trees as fewer rotations are done due to relatively relaxed balancing.
• AVL trees store balance factors or heights with each node, thus requires storage for an integer per node whereas Red Black Tree requires only 1 bit of information per node.
• Red Black Trees are used in most of the language libraries likemap, multimap, multisetin C + +whereas AVL trees are used in databaseswhere faster retrievals are required.
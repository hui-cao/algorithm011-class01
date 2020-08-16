#学习笔记 W08

# 16. 位运算

十进制和二进制如何相互转换？[如何从十进制转换为二进制](https://zh.wikihow.com/%E4%BB%8E%E5%8D%81%E8%BF%9B%E5%88%B6%E8%BD%AC%E6%8D%A2%E4%B8%BA%E4%BA%8C%E8%BF%9B%E5%88%B6)

位运算符：`<< >> | & ~ ^`

## XOR - 异或

异或：相同为 0，不同为 1。也可用 “ 不进位加法 ” 来理解。
异或操作的一些特点：
```
x ^ 0 = x
x ^ 1s =  ~x //1s = ~0
x ^ (~x) = 1s
x ^ x =  0
c = a ^ b  ->  a ^ c = b, b ^ c = a //交换两个数
a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c //associative
```

## 指定位置的位运算

1. 将 x 最右边的 n 位清零：`x & ( ~0 << n)`
2. 获取 x 的第 n 位值（0 或者 1）： (x > >  n) & 1
3. 获取 x 的第 n 位的幂值：x& (1 < <n)
4. 仅将第 n 位置为 1：x |  (1 < <  n)
5. 仅将第 n 位置为 0：x & ( ~ (1 < <  n))
6. 将 x 最高位至第 n 位（含）清零：x& ((1 < <  n) -1)
7. 将第 n 位至第 0 位（含）清零：x& ( ~ ((1 < <  (n + 1)) -1))

## 参考链接
*  [N 皇后位运算代码示例](https://shimo.im/docs/YzWa5ZZrZPYWahK2) 

## 实战题目 / 课后作业
*  [位 1 的个数](https://leetcode-cn.com/problems/number-of-1-bits/) （Facebook、苹果在半年内面试中考过）
*  [2 的幂](https://leetcode-cn.com/problems/power-of-two/) （谷歌、亚马逊、苹果在半年内面试中考过）
*  [颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/) （苹果在半年内面试中考过）
*  [N 皇后](https://leetcode-cn.com/problems/n-queens/description/) （字节跳动、亚马逊、百度在半年内面试中考过）
*  [N 皇后 II ](https://leetcode-cn.com/problems/n-queens-ii/description/) （亚马逊在半年内面试中考过）
*  [比特位计数](https://leetcode-cn.com/problems/counting-bits/description/) （字节跳动、Facebook、MathWorks 在半年内面试中考过）

---

# 17. 布隆过滤器和LRU缓存

# 布隆过滤器

## 参考链接
*  [布隆过滤器的原理和实现](https://www.cnblogs.com/cpselvis/p/6265825.html) 
*  [使用布隆过滤器解决缓存击穿、垃圾邮件识别、集合判重](https://blog.csdn.net/tianyaleixiaowu/article/details/74721877) 
*  [布隆过滤器 Python 代码示例](https://shimo.im/docs/UITYMj1eK88JCJTH) 
*  [布隆过滤器 Python 实现示例](https://www.geeksforgeeks.org/bloom-filters-introduction-and-python-implementation/) 
*  [高性能布隆过滤器 Python 实现示例](https://github.com/jhgg/pybloof) 
*  [布隆过滤器 Java 实现示例 1](https://github.com/lovasoa/bloomfilter/blob/master/src/main/java/BloomFilter.java) 
*  [布隆过滤器 Java 实现示例 2](https://github.com/Baqend/Orestes-Bloomfilter) 

# LRU缓存

## 参考链接
*  [Understanding the Meltdown exploit](https://www.sqlpassion.at/archive/2018/01/06/understanding-the-meltdown-exploit-in-my-own-simple-words/) 
*  [替换算法总揽](https://en.wikipedia.org/wiki/Cache_replacement_policies) 
*  [LRU Cache Python 代码示例](https://shimo.im/docs/CoyPAyXooGcDuLQo) 
## 实战题目 / 课后作业
*  [LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/#/) （亚马逊、字节跳动、Facebook、微软在半年内面试中常考）

---

# 18. 排序算法

## 归并排序
```java
/**
 * 归并排序
 * time -> O(nlogn) | space -> O(n)
 */
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; // (left + right) / 2

    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
    int[] temp = new int[right - left + 1]; // 中间数组
    int i = left, j = mid + 1, k = 0;

    while (i <= mid && j <= right) {
        temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
    }

    while (i <= mid)   temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];

    // System.arraycopy(temp, 0, arr, left, temp.length);
    for (int p = 0; p < temp.length; p++) {
        arr[left + p] = temp[p];
    }
```


## 参考链接
*  [十大经典排序算法](https://www.cnblogs.com/onepixel/p/7674659.html) 
*  [快速排序代码示例](https://shimo.im/docs/TX9bDbSC7C0CR5XO) 
*  [归并排序代码示例](https://shimo.im/docs/sDXxjjiKf3gLVVAU) 
*  [堆排序代码示例](https://shimo.im/docs/M2xfacKvwzAykhz6) 

## 课后作业
用自己熟悉的编程语言，手写各种初级排序代码，提交到学习总结中。


## 参考链接
*  [十大经典排序算法](https://www.cnblogs.com/onepixel/p/7674659.html) 
*  [9 种经典排序算法可视化动画](https://www.bilibili.com/video/av25136272) 
*  [6 分钟看完 15 种排序算法动画展示](https://www.bilibili.com/video/av63851336) 
## 实战题目 / 课后作业
*  [数组的相对排序](https://leetcode-cn.com/problems/relative-sort-array/) （谷歌在半年内面试中考过）
*  [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) （Facebook、亚马逊、谷歌在半年内面试中考过）
*  [力扣排行榜](https://leetcode-cn.com/problems/design-a-leaderboard/) （Bloomberg 在半年内面试中考过）
*  [合并区间](https://leetcode-cn.com/problems/merge-intervals/) （Facebook、字节跳动、亚马逊在半年内面试中常考）
*  [翻转对](https://leetcode-cn.com/problems/reverse-pairs/) （字节跳动在半年内面试中考过）

- [ ] 两个有序的链表如何合并？
- - - -

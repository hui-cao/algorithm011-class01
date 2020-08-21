# 学习笔记 W09

# 19. 高级动态规划


[一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/)

## 参考链接
- [x] [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) （阿里巴巴、腾讯、字节跳动在半年内面试常考）
- [x] [不同路径](https://leetcode-cn.com/problems/unique-paths/) （亚马逊、微软、Facebook 在半年内面试中考过）
- [x] [打家劫舍](https://leetcode-cn.com/problems/house-robber/) （字节跳动、谷歌、苹果在半年内面试中考过）
- [x] [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/) （字节跳动、谷歌、亚马逊在半年内面试中考过）
- [x] [股票买卖](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) （字节跳动、亚马逊、Facebook 在半年内面试常考）

## 课后作业
在学习总结中，写出 [63. 不同路径 2 ](https://leetcode-cn.com/problems/unique-paths-ii/) 这道题目的状态转移方程。

```
状态转移方程: 
dp[i][j] = dp[i−1,j] + dp[i,j−1]; (i,j)上无障碍物
dp[i][j] = 0; (i,j)上有障碍物
```

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length, n = obstacleGrid[0].length;
    int[] dp = new int[n];
    dp[0] = 1;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[i][j] == 1) dp[j] = 0;
            else if (j - 1 >= 0) dp[j] += dp[j-1];
        }
    }
    return dp[n-1];
}
```

---


#排序
冒泡排序
快速排序
归并排序
堆排序


#二叉树
二叉树前中后层遍历

#归并
爬台阶
凑零钱
敲键盘


#dfs
如何解决使用多次、重复元素、排列顺序


全排列
元素只能用一次used

全排列（有重复元素）
只能用一次used 如 aaa bbb
跳过重复元素sort+ [i]==[i-1] + !used[i-1] 如 abb abb

子集
元素只能用一次used
过滤顺序不同元素相同的子集 begin=depth 如 abc:acb, ab:ba

子集（有重复元素）
元素只能用一次used
跳过重复元素sort+ [i]==[i-1] + !used[i-1]
过滤顺序不同元素相同的子集 begin=depth

组合求和 
剪枝sort+ target-nums[i]<0
过滤顺序不同元素相同的子集 begin=depth

组合求和（有重复值）
剪枝sort+ target-nums[i]<0
元素只能用一次used
跳过重复元素sort+ [i]==[i-1] + !used[i-1]
过滤顺序不同元素相同的子集 begin=depth 


# bfs
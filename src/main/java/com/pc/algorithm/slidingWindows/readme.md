





###1.缩小条件
- 最小覆盖子串：vaild==needs
- 最大无重复子串：windows.get(add)>1
- 异位排列：right-left == p.length

###2.结果获取
* 最小覆盖子串：vaild==needs
* 最大无重复子串：没出现重复就一直加，出现重复就去掉重复重新开始加
* 异位：长度一致后，判断vaild==needs

###3.窗口更新方式
+ 最小覆盖子串：needs.containsKey
+ 最大无重复子串：无
+ 异位/排列：needs.containsKey


最小覆盖子串
```
while(right<s.length()) {
	char add = s.charAt(right);
	//增加窗口needs.containsKey
	right++;

	//条件是窗口中是否包含所有子串
	while(vaild==p.size()) {

		//比较结果 right-left ? len

		char add = s.charAt(left);

		//缩小窗口

		left++;
	}
}
```

无重复字符的最长子串

```
while(right<s.length()) {

	char add = s.charAt(right);

	//扩展窗口

	right++;

	//当新加的元素重复，说明不能加了，缩小窗口，直到剔除重复元素
	while(windows.get(add)>1) {

		//这里结果还没出来

		char rmv = s.charAt(left);

		//缩小窗口

		left++;
	}

	//比较结果 right-left ? len
}

```



找所有异位词索引/包含全排列

```
while(right<s.length()) {

	char add = s.charAt(right);

	//增加窗口

	right--;

	//只要长度不满足，就可以跳出了
	while(right-left==p.length()) {

		//如果 vaild==needs ,结果

		char rmv = s.charAt(left);

		//减小窗口

		left++;
	}
	
}
```



### 双指针

环形链表
快慢指针
相遇，调整速度，再次相遇

前后指针
一个先走，一个后走

回文判断
转成数组，前后指针



### 反转链表

递归
```
ListNode reverse(head) {
    //base case
   
    
    newHead = reverse(head.next)
    
    head.next.next = head;
    head.next = null;
    
    return newHead;
}

```

迭代
```
prev = null;
curr = head;

while(curr!=null) {
    next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}

```

反转n~m

```

reverseBetween(head,m,n) {
    //base case
    if(m==1) {
        return reverseN(head,n);
    }
    
    head.next = reverseN(head,m-1,n-1);
    return head;
}

successor;
reverseN(head,n) {
    //base case
    if(n==1) {
        successor = head.next;
        return head;
    }
    
    newHead = reverseN(head.next,n-1);
    
    head.next.next = head;
    head.next = successor;
    
    returun newHead;

}

```


k个一组反转
```

reverseKGroup(head,k) {

    if(head==null) {
        return null;
    }
    
    tail
    
    //k个一组反转,不到k个不反转
    for(k) {
        tail = tail.next;
    }
    
    newHead = revsese(head,tail);
    
    //反转完 要把当前的尾巴和后面的尾巴连起来
    head.next = reverseKGroup(tail,k);
    
    
    return newHead;
    
}

```
# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)
1. Use stack to return the elements in the list and to flatten the list. 
2. Add the intergers in the reverse order in the stack. Using inbuild methods provided by integers IsInteger() to check whether the peek is integer or not.
3. If its an integer then return that, otherwise again faltten the list.


## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)
1. Using a Map<Integer, Node> to store the integer and corresponding cache. Where node is a doubly linked list having access to both its next and previous.
2. Considering head.next as the latest accessed element and tail.previous as least accessed element.
3. If put method is called, Put the element in the map and its its reference to head.next.
4. If get is called, check whether that element is present in the map or not. If not, return -1, if it is present then return that element and put its reference to head.next.




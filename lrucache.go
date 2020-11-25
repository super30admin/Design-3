// Time Complexity : O(1)
// Space Complexity : O(2n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/*
we need a doubly linkedlist and a map to solve this
create lrunode with key, value, prev and next pointers along with constructor

in constructor of lrucache have just dummy head and tail nodes, capacity and the map

we need 2 helper functions one adds the node after head, another just removes the given node

to get
if map has key then remove and put the current element at head and return elements value
else return -1

to put
if map has the key then update its value and remove and put the element after the head
else
if num of element in map >= capacity then remove the element from map and the element just before tail(lru)
just create and add the new element to map and just after head
*/
package main

import "fmt"

type LRUNode struct {
	key int
	val int
	prev *LRUNode
	next *LRUNode
}

func LRUNodeConstructor(key1, val1 int) *LRUNode {
	return &LRUNode{
		key: key1,
		val: val1,
	}
}

type LRUCache struct {
	m map[int]*LRUNode
	cacheCapacity int
	head *LRUNode
	tail *LRUNode
}


func LRUConstructor(capacity int) LRUCache {
	x := LRUNodeConstructor(-1, -1)
	y := LRUNodeConstructor(-1, -1)
	x.next = y
	y.prev = x
	return LRUCache{
		m: make(map[int]*LRUNode),
		cacheCapacity: capacity,
		head: x,
		tail: y,
	}
}


func (this *LRUCache) Get(key int) int {
	ele, e := this.m[key]
	if e {
		this.removeLRUNode(ele)
		this.putLRUNodeAtHead(ele)
		return ele.val
	}
	return -1
}


func (this *LRUCache) Put(key int, value int)  {
	ele, e := this.m[key]
	if e {
		ele.val = value
		this.removeLRUNode(ele)
		this.putLRUNodeAtHead(ele)
	} else {
		if len(this.m) >= this.cacheCapacity {
			temp := this.tail.prev
			delete(this.m, temp.key)
			this.removeLRUNode(temp)
		}
		temp := LRUNodeConstructor(key, value)
		this.m[key] = temp
		this.putLRUNodeAtHead(temp)
	}
}

func (this *LRUCache) putLRUNodeAtHead(n *LRUNode) {
	n.next = this.head.next
	this.head.next = n
	n.next.prev = n
	n.prev = this.head
}

func (this *LRUCache) removeLRUNode(n *LRUNode) {
	n.prev.next = n.next
	n.next.prev = n.prev
}

func MainLRUCache() {
	obj := LRUConstructor(2)
	obj.Put(1, 1)
	obj.Put(2, 2) // cache is {1=1, 2=2}
	fmt.Println(obj.Get(1))    // return 1
	obj.Put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
	fmt.Println(obj.Get(2))    // returns -1 (not found)
	obj.Put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
	fmt.Println(obj.Get(1))    // return -1 (not found)
	fmt.Println(obj.Get(3))    // return 3
	fmt.Println(obj.Get(4))    // return 4
}

## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)


#Approach
# Hashmap will contain key and reference node. From reference node, get the location in LinkedList. Once we have the location, we need to put the node in front of head
# To avoid traversing through it again, use doubly Linked list. In get(), if node not in hashmap, return -1. If present, remove that node and add it in front of head
# In put(), if node present in hashmap, remove it, update its value and add it back. if len(hmap) == capacity, remove last node before tail & del from hashmap


# Time Complexity : O(1)
# Space Complexity : O(capacity)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

class ListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hmap = {}
        self.head = ListNode(-1,-1)
        self.tail = ListNode(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def add(self,node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if not key in self.hmap:
            return -1
        
        node = self.hmap[key]
        self.remove(node)
        self.add(node)
        return node.val
        

    def put(self, key: int, value: int) -> None:
        if key in self.hmap:
            old_node = self.hmap[key]
            self.remove(old_node)
        
        node = ListNode(key,value)
        self.hmap[key] = node
        self.add(node)

        if len(self.hmap)>self.capacity:
            node_del = self.tail.prev
            del self.hmap[node_del.key]
            self.remove(node_del)
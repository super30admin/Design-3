## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

# Time Complexity : O(1)
# Space Complexity : O(capacity)
# Did this code successfully run on GFG : YES
# Any problem you faced while coding this : NO


class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)

        self.head.next = self.tail
        self.tail.prev = self.head

        self.cap = capacity
        self.hm = {}
        
    def removeNode(self,node): # O(1)
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def addafterHead(self, node): #O(1)
        node.next = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next.prev = node

    def get(self, key: int) -> int: # TC: O(1) SC: O(capacity)
        if key not in self.hm:
            return -1
        
        node = self.hm[key]
        self.removeNode(node)
        self.addafterHead(node)

        return node.val
        

    def put(self, key: int, value: int) -> None: # TC:O(1) and SC: O(capacity)
        if key in self.hm:
            node = self.hm[key]
            self.removeNode(node)
            self.addafterHead(node)
            node.val = value
        else:
            if(len(self.hm) == self.cap):
                tailprev = self.tail.prev
                self.removeNode(tailprev)
                del self.hm[tailprev.key]

            node = Node(key,value)
            self.addafterHead(node)
            self.hm[key] = node

# TC: GET AND PUT: O(1)
# SC: GET AND PUT: O(capacity)

        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
'''
====== Submission Details =======
Student Name: Pavan Kumar K. N.
S30 SlackID : RN32MAY2021
=================================
'''

# 146. LRU Cache

# https://leetcode.com/problems/lru-cache/

#-----------------
# Time Complexity: 
#-----------------
# O(1) - get and put()

#------------------
# Space Complexity: 
#------------------
# O(N): Space to store hashmap and doubly linked list

#-----------------------
# Leet Code Performance: 
#-----------------------
# Ran Successfully?: Yes

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):        
        self.map = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
    
    def addToHead(self, node) -> None:
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
    
    def removeNode(self, node) -> None:
        node.next.prev = node.prev
        node.prev.next = node.next

    def get(self, key: int) -> int:
        if not key in self.map:
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.val
        

    def put(self, key: int, value: int) -> None:
        if  key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if self.capacity == len(self.map.keys()):
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.map[tailPrev.key]
            
            newNode = Node(key, value)
            self.addToHead(newNode)
            self.map[key] = newNode
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
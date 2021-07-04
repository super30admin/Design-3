"""
Time Complexity : removeNode() - O(1)
                  addHead - O(1)
                  get() - O(1)
                  put - O(1)
Space Complexity : O(n) - where n is the capacity
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this: none
"""

class LRUCache:
    class Node:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None
    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}
        self.head = self.Node(-1,-1)
        self.tail = self.Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def removeNode(self, p):
        p.prev.next = p.next
        p.next.prev = p.prev
        
    def addHead(self, p):
        p.next = self.head.next
        self.head.next.prev = p
        p.prev = self.head
        self.head.next = p
    
    def get(self, key: int) -> int:
        if self.hashmap.get(key):
            node = self.hashmap.get(key)
            self.removeNode(node)
            self.addHead(node)
            return node.value
        return -1

    def put(self, key: int, value: int) -> None:
        node = self.Node(key, value)
        
        if self.hashmap.get(key) != None:
            node = self.hashmap.get(key)
            node.value = value
            self.removeNode(node)
            self.addHead(node)
        else:          
            if len(self.hashmap) < self.capacity:
                self.hashmap[key] = node
                self.addHead(node)
            else:
                leastUsed = self.tail.prev
                self.removeNode(leastUsed)
                self.hashmap.pop(leastUsed.key, None)
                self.addHead(node)
                self.hashmap[key] = node
                
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
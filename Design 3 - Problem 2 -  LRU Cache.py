"""
FAANMG Problem #81 {Medium } 

146. LRU Cache


Time Complexity : O(1)
Space Complexity : O(C), C = capacity


Did this code successfully run on Leetcode : Yes


@name: Rahul Govindkumar_RN27JUL2022
""" 

class Node:
    
    def __init__(self,key,val):
        
        self.key = key
        self.val = val
        self.next = None
        self.prev = None
        
  #LRU Left -> Head
    # Right -> tail Most Recently used   
class LRUCache:

    def __init__(self, capacity: int):
        
        self.cache = {}
        self.capacity= capacity
        
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.length = 0
        
    
    def remove(self,node):
        prev , nxt = node.prev , node.next
        
        prev.next , nxt.prev = nxt, prev
        self.length -= 1
        
    def insert(self, node):
        prev , nxt = self.tail.prev , self.tail
        
        prev.next = nxt.prev = node
        
        node.next , node.prev = nxt , prev
        self.length += 1
        
        
    def get(self, key: int) -> int:
        
        if key in self.cache:
            self.remove(self.cache[key])
            self.insert(self.cache[key])
            return self.cache[key].val
        
        return -1
        

    def put(self, key: int, value: int) -> None:
        
        if key in self.cache:
            self.remove(self.cache[key])
            
        self.cache[key] = Node(key,value)
        
        self.insert(self.cache[key])
        
        if self.length > self.capacity:
            lru = self.head.next
            
            self.remove(lru)
            
            del self.cache[lru.key]
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
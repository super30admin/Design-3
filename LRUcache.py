#TC: O(1) for both get and put
#SC: O(n) where n is capacity
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

class doublyNode:
    
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.map = {}       #cache
        self.capacity = capacity
        self.head = doublyNode(0,0)
        self.tail = doublyNode(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.count = 0
        
    def removeNode(self,node): #remove  - LRU
        node.prev.next = node.next
        node.next.prev = node.prev
        
    
    def addNode(self,node): #add to the beginning - MRU
        node.next = self.head.next
        node.next.prev = node
        node.prev = self.head
        self.head.next = node
        
    
    def get(self, key: int) -> int:  
        if key in self.map:
            node = self.map[key]
            res = node.val
            #put this in most recently used loc i.e front of dll
            self.removeNode(node)
            self.addNode(node)
            return res
        return -1
            
            
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addNode(node)
        else:
            node = doublyNode(key,value)
            self.map[key] = node
            if self.count<self.capacity:
                self.count+=1
                self.addNode(node)
            else:
                del self.map[self.tail.prev.key]
                self.removeNode(self.tail.prev)
                self.addNode(node)
                
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
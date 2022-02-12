'''
Solution:
1.  In order to design LRU Cache, we need Doubly-Linked-List to store cache which makes it O(1) operation to remove a
    node from any position and also to insert at the Head (this is the place to store most recently accessed element).
2.  We also use a HashMap to store node's key and its address (complete Node object) for faster access.
3.  Two helper functions to remove node and insert node at head.
Time and Space Complexities of all functions are O(1)
--- Passed all testcases successfully on Leetcode.
'''

class Node(object):
    
    def __init__(self, key, val):
        self.val = val
        self.key = key
        next = None
        prev = None

class LRUCache(object):
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        
        self.hMap = {}
    
    def addToHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        
        
        self.head.next = node
        node.next.prev = node
        
        return
    
    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
        return
                

    def get(self, key: int) -> int:
        if (key not in self.hMap):
            return -1
        else:
            node = self.hMap[key]
            self.removeNode(node)
            self.addToHead(node)

            return node.val
        
        

    def put(self, key: int, value: int) -> None:
        
        if key in self.hMap:
            self.hMap[key].value = value
            node = self.hMap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
            
        else:
            if (self.capacity == len(self.hMap)):
                tailPrev = self.tail.prev
                self.removeNode(tailPrev )
                #remove from hashmap
                self.hMap.pop(tailPrev .key)
            
            self.hMap[key] = Node(key ,value)
            node = self.hMap[key]
            self.addToHead(node)
        
        return
            
                
                
            
            
                


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# APPROACH: Using a Python Dictionary + Double LinkedList 

# Time Complexity : O(1)
# Space Complexity : O(N), where N is Capacity
# Did this code successfully run on Leetcode : 
# Using a List, the get operation of removing an item is O(N) and adding the element to the end of list is O(1), the complexity is therefore O(N). To reduce this complexity we can use a HashMap with Doubly LinkedList inside it.
       
class Node:
    def __init__(self, k, v):
        self.key = k
        self.val = v
        self.previous = None
        self.next = None

class LRUCache:
    def __init__(self, capacity):
        self.d = dict()
        self.head = Node(-1, -1)     #dummy node for head
        self.tail = Node(-1,-1)      #dummy node for tail
        self.head.next = self.tail
        self.tail.previous = self.head
        self.capacity = capacity
        self.size = 0
        
    def removeNode(self, node):
        node.previous.next = node.next
        node.next.previous = node.previous
        
    def addToHead(self, node):
        node.next = self.head.next
        self.head.next = node
        node.previous = self.head
        node.next.previous = node
        
    def get(self, key):
        if key not in self.d:
            return -1
        node = self.d[key]
        
        #Updating the cache order
        self.removeNode(node)
        self.addToHead(node)
        return node.val
        
    def put(self, key, value):
        if key not in self.d:
            self.d[key] = Node(key, value)
        else:
            self.removeNode(self.d[key])
            self.size -= 1
            self.d[key] = Node(key, value)
            
        node = self.d[key]
        
        if self.size < self.capacity:
            self.addToHead(node)
            self.size += 1
        else:    
            del self.d[self.tail.previous.key]
            self.removeNode(self.tail.previous)
            self.addToHead(node)
            
           
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

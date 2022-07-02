'''
-- Passed test cases on Leetcode
'''
class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity

        self.head = Node(-1, -1)       
        self.tail = Node(-1, -1)       
        self.head.next = self.tail     
        self.tail.prev = self.head     
        self.keyNodeMap = {}   
        
    def insertAtHead(self, node):
        
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

        return
    
    def remove(self, node):
        
        node.prev.next = node.next
        node.next.prev = node.prev

        return

        
    def get(self, key: int) -> int:
        #O(1) - Time and space complexity
        if (key not in self.keyNodeMap):       
            return -1
        else:                                   
            node = self.keyNodeMap[key]         
            self.remove(node)                 
            self.insertAtHead(node)          
            return node.value    
        
    def put(self, key: int, value: int) -> None:
        #O(1) - Time and space complexity
        
        if (key in self.keyNodeMap):               
            self.keyNodeMap[key].value = value
            node = self.keyNodeMap[key]            

            self.remove(node)                    
            self.insertAtHead(node)               

        else:
            if (len(self.keyNodeMap) == self.capacity): 
                node = self.tail.prev                   
                self.remove(node)                    
                self.keyNodeMap.pop(node.key)          

            self.keyNodeMap[key] = Node(key, value)     
            node = self.keyNodeMap[key]                
            self.insertAtHead(node)                  
        return
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

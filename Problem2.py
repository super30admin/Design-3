"""
// Time Complexity : o(1)
// Space Complexity : o(1) - constant as capacity is known
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

"""
from collections import defaultdict
class Node:
    def __init__(self,k, v):
        self.key = k
        self.val = v
        self.prev = None
        self.next = None
    
class LRUCache:
    def __init__(self, capacity: int):
        self.map = defaultdict() #keeps track of key and reference of the node in the LL
        self.capacity = capacity
        self.head = Node(-1,-1) # dummy head node
        self.tail = Node(-1,-1) #dummy tail node
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def remove_node(self, n):
        n.prev.next = n.next
        n.next.prev = n.prev
        
    def add_to_head(self, n): #most recently used nodes will be placed at head
        n.next = self.head.next
        self.head.next = n
        n.prev = self.head
        n.next.prev = n
        
    def get(self, key: int) -> int:
        if key not in self.map: #if key not present in map, it doesnt exist in cache
            return -1
        
        ref = self.map[key] #get the node reference from map
        val = ref.val #get value
        
        self.remove_node(ref) # place the node at head to make it most recently used
        self.add_to_head(ref)
        
        return val
    
    def put(self, key: int, value: int) -> None:
        
        if key in self.map: #if key already present, update the value and make it most recently used
            ref = self.map[key]
            ref.val = value
            
            self.remove_node(ref)
            self.add_to_head(ref)
        else: #else, check if capacity of cache isnt being exceeded
            if len(self.map) == self.capacity:
                tail_ref = self.tail.prev
                
                self.remove_node(tail_ref)
                self.map.pop(tail_ref.key)
                
            node = Node(key,value)
            self.map[key] = node
            self.add_to_head(node)
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
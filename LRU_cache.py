# Time Complexity : Add - O(1) 
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
'''
1. Maintaina hashmap to access the reference to an element in O(1), and a D.LinkedList to store the order

2. Anytime the key is inserted, if key not already there, it is appended at the end of LinkedList using tail, however if capacity is full node towards the head is removed both LL and hashmap
3. If exists, acces the reference and change its value, and put it to the tail
4.  To get an elment get reference from hashmap,return the elemnt, and put it towards the tail of LL
'''

class Node:
    def __init__(self, key=None, val=None):
        
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        
        self.capacity = capacity
        self.hashmap = {}
        self.head = Node()
        self.tail = Node()
        
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        
        if key not in self.hashmap:
            return -1
        
        node_ref = self.hashmap[key]
        val = node_ref.val
        
        self.extract_node(node_ref)
        self.insert_at_tail(node_ref) 
        
        return val
            

    def extract_node(self, node):
        
        node.prev.next = node.next
        node.next.prev = node.prev
        
    def insert_at_tail(self, node):
        
        node.prev = self.tail.prev
        node.next = self.tail
        
        node.prev.next = node
        node.next.prev = node
        
        
    def put(self, key: int, value: int) -> None:
        
        if key in self.hashmap:
            
            node_ref = self.hashmap[key]
            node_ref.val = value
            
            self.extract_node(node_ref)
            self.insert_at_tail(node_ref)
            
        else:
            
            if len(self.hashmap) == self.capacity:
                #may explode so delte node at head
                
                temp = self.head.next
                
                del self.hashmap[temp.key]
                temp.prev.next = temp.next
                temp.next.prev = temp.prev
                
                
            new_node = Node(key, value)
            self.insert_at_tail(new_node)
            self.hashmap[key] = new_node
                
                

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# Acceptable Solution:

class LRUCache:

    """
    Description: create LRU Cache with get and put
    
    Time Complexicity: O(n) 
    Space Complexicity: O(1)
    
    Works in leetcode: yes with low percentile of time complexicity
    
    Approach:
    - add capacity and data as a list of tuples (key, value pairs) to __init__
    - for put: check if the key is present or not
      + if yes, remove the data from there and add new (key, value) at the end of the list
      + if no, add new (key, value) at the end, if the size of list becomes larger than capacity, pop the first item (least used)
    - for get: check if the key is present of not
      + if yes, return the value and move (key, value) to the end of the list
      + if no, return -1 (as instructed)
    """

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.data = []

    def get(self, key: int) -> int:
        for i, (k, v) in enumerate(self.data):
            if k == key:
                self.data.pop(i)
                self.data.append((k, v))
                return v
        return -1

    def put(self, key: int, value: int) -> None:
        has_key = False
        for i, (k, v) in enumerate(self.data):
            if k == key:
                has_key = True
                self.data.pop(i)
                self.data.append((key, value))
        if not has_key:
            self.data.append((key, value))
            
        if len(self.data) > self.capacity:
            self.data.pop(0)
           
# Optimized Solution:

"""
Still working - not accepted in LeetCode yet
"""

class ListNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}
        
        # add dummy nodes in begining and end of the linked list
        self.head = ListNode(-1, -1)
        self.tail = ListNode(-1, -1)
        
        # create doubly-linked connection
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def get(self, key: int) -> int:
        if key in self.cache.keys():
            node = self.cache[key]
            self.remove_node(node)
            self.add_node(node)
            return node.value
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache.keys():
            print(key, self.cache[key].value)
            node = self.cache[key]
            self.remove_node(node)
            self.add_node(node)
        else:
            self.cache[key] = ListNode(key, value)
            self.add_node(self.cache[key])
            if len(self.cache) > self.capacity:
                self.remove_node(self.head.next)
            
    def remove_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev     
        
    def add_node(self, node):
        # to add node before tail (dummy node)
        self.tail.prev.next = node
        node.next = self.tail
        node.prev = self.tail
        self.tail.prev = node
        
        """ code to add node in front    
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node
        """
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

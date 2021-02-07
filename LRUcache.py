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

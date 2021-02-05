# -*- coding: utf-8 -*-
"""
TC : O(N) where N is the size of elements required to create a hashmap, O(1) for hashmap lookup
SC : O(N) N - number of elements required to create a hashmap
"""

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.data = []
        
    def get(self, key: int) -> int:
        for i, t in enumerate(self.data):
            if t[0] == key:
                values = t
                self.data.pop(i)
                self.data.append(values)
                return values[1]
            
        return -1
    
    def put(self, key: int, value: int) -> None:
        did_put = False
        for i, t in enumerate(self.data):
            if t[0] == key:
                did_put = True
                self.data.pop(i)
                self.data.append((key, value))
        if not did_put:
            self.data.append((key, value))
        if len(self.data) > self.capacity:
            self.data.pop(0)
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
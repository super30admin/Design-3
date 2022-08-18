"""
Runtime Complexity: O(1)-  since both the put and get method works in constant time because of the special data structure called orderedDict which is nothing but LinkedHashmap in Java.
Space Complexity: O(capacity)
Yes, the code worked on leetcode.
Issues while coding - No
"""
from collections import OrderedDict
class LRUCache(OrderedDict):

    def __init__(self, capacity: int):
        self.capacity = capacity
        

    def get(self, key: int) -> int:
        if key not in self:
            return -1
        self.move_to_end(key)   #move the key to the end to keep unused key at the front
        return self[key]
        

    def put(self, key: int, value: int) -> None:
        if key in self:
            self.move_to_end(key)   #move the key to the end.
        self[key] = value       #update the value
        #print(self)
        if len(self)>self.capacity:
            self.popitem(last=False)    #pop unused element from the front
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# Time Complexity : O(1) 
# Space Complexity : O(N) where N is the number of elements in the cache
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using an OrderedDict to store the key value pairs in the cache. 
# The OrderedDict maintains the order of the keys based on their usage.
# When get is called, I check if the key is in the OrderedDict. If it is, I move the key to the end of the OrderedDict to mark it as recently used and return the value.
# If the key is not in the OrderedDict, I return -1.
# When put is called, I check if the key is in the OrderedDict. If it is, I update the value and move the key to the end of the OrderedDict to mark it as recently used.
# If the key is not in the OrderedDict, I add the key value pair to the OrderedDict.
# If the size of the OrderedDict exceeds the capacity, I remove the first key value pair from the OrderedDict which is the least recently used key value pair.

from collections import OrderedDict
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.ord = OrderedDict()
        

    def get(self, key: int) -> int:
        if key in self.ord:
            self.ord.move_to_end(key,last=True)
            return self.ord[key]
        return -1
    def put(self, key: int, value: int) -> None:
        if key in self.ord:
            self.ord[key] = value
            self.ord.move_to_end(key,last = True)
        else:
            self.ord[key] = value
            if len(self.ord) > self.capacity:
                self.ord.popitem(last = False)
        
        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
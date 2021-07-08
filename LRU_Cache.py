"""
// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
Algorithm explanation
We leveraged the use of OrderedDict to keep track of least recently
used elements and most recently used items
Initial configuration - cache - OrderedDict, size of cache
- get
    -  Since we are acccessing the element(if found), we update the status to 
    most recently used by moving the key to end of the cache map
    - Else return -1
- put
    - If we find the key, we move the key to end of cache,(Most recently used)
    - If the size of cache matches given limit, remove the element at the
    front of the cache(Least recently used)
    - Update cache[key] = val 
"""
from collections import OrderedDict
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = OrderedDict()
        self.limit = 0
        self.order = 0

    def get(self, key: int) -> int:
        result = -1
        val = self.cache.get(key)
        if not val:
            return -1
        self.cache.move_to_end(key)
        return val

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            #since element is accessed it is most recently used status upgraded
            self.cache.move_to_end(key)
        elif len(self.cache) ==  self.capacity:
            self.cache.popitem(last=False) # deleting the front element from dict (least recently used)
        self.cache[key] = value

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
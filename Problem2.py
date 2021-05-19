## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

from collections import OrderedDict
class LRUCache:

    def __init__(self, capacity: int):
        self.cache = OrderedDict()
        self.capacity = capacity

    def get(self, key: int) -> int:
        val = self.cache.get(key, None)
        if val is None:
            return -1
        self.cache[key] = val
        self.cache.move_to_end(key, False)
        return val

    def put(self, key: int, value: int) -> None:
        self.cache[key] = value
        self.cache.move_to_end(key, False)
        if len(self.cache) > self.capacity:
            self.cache.popitem(True)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
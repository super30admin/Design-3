#LRU Cache

# Use orderdDict of python to manage the ordering of keys added, if a key is accessed or updated move the key to last in the order
# Space -> On where n is the input key value pair
# time for each is O1

from collections import OrderedDict

class LRUCache:
    cache = None
    maxCapacity = 0

    def __init__(self, capacity: int):
        self.cache = OrderedDict()
        self.maxCapacity = capacity
        

    def get(self, key: int) -> int:
        if key in self.cache:
            self.cache.move_to_end(key)
            return self.cache[key]
        return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.cache.move_to_end(key)
        self.cache[key] = value
        
        if len(self.cache) > self.maxCapacity:
            self.cache.popitem(False)
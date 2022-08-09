from collections import OrderedDict
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.LRUCache = OrderedDict()

    def get(self, key: int) -> int:
        if not key in self.LRUCache:
            return -1 
        self.LRUCache.move_to_end(key)
        return self.LRUCache[key]

    def put(self, key: int, value: int) -> None:
        if key in self.LRUCache:
            self.LRUCache.move_to_end(key)
        self.LRUCache[key] = value
        if len( self.LRUCache ) > self.capacity:
            self.LRUCache.popitem(last = False)

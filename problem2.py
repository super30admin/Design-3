 # Space Complexity: O(N)
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = OrderedDict()
        

    def get(self, key: int) -> int: #O(1)
        if key not in self.cache:
            return -1
        else:
            # Move the accessed key to the end (most recent) of the OrderedDict
            self.cache.move_to_end(key)
            return self.cache[key]

        

    def put(self, key: int, value: int) -> None: #O(1)
        if key in self.cache:
            # Update the value and move the key to the end (most recent) of the OrderedDict
            self.cache[key] = value
            self.cache.move_to_end(key)
        else:
            # If the cache is at its capacity, remove the least recently used element (first item)
            if len(self.cache) >= self.capacity:
                self.cache.popitem(last=False)
            # Add the new key-value pair to the end (most recent) of the OrderedDict
            self.cache[key] = value
        

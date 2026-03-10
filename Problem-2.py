# Time Complexity : O(1) for get and put operations
# Space Complexity : O(N), N = capacity of the cache (number of key-value pairs stored)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# We implement the LRU Cache using Python's OrderedDict.
# OrderedDict maintains the insertion order. When a key is accessed or updated,
# we move it to the end to mark it as recently used. When the cache exceeds
# capacity, we remove the first item (least recently used) to maintain the LRU property.

from collections import OrderedDict

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = OrderedDict()  # Maintains the order of keys

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        # Move the key to the end to mark it as recently used
        self.cache.move_to_end(key)
        return self.cache[key]

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            # Update the value and mark as recently used
            self.cache.move_to_end(key)
        self.cache[key] = value
        if len(self.cache) > self.capacity:
            # Pop the first item (least recently used)
            self.cache.popitem(last=False)


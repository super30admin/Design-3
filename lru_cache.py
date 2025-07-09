# Time Complexity : O(1) average for both get and put using OrderedDict
# Space Complexity : O(capacity), where capacity is the max number of items stored
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# I used Python’s OrderedDict to maintain insertion order.
# When I access an item (via get or put), I move it to the end to mark it as recently used.
# When capacity is exceeded, I removed the first item (least recently used) using popitem(last=False).

from collections import OrderedDict

class LRUCache:

    def __init__(self, capacity: int):
        self.cache = OrderedDict()
        self.capacity = capacity

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        # Move to the end to mark as recently used
        self.cache.move_to_end(key)
        return self.cache[key]

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            # Update and move to end
            self.cache.move_to_end(key)
        self.cache[key] = value
        if len(self.cache) > self.capacity:
            # Remove least recently used (first item)
            self.cache.popitem(last=False)

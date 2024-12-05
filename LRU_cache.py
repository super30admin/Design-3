# Explain your approach in briefly only at top of your code
# Approach:
# To implement an LRU Cache with O(1) average time complexity for `get` and `put`, 
# we use a combination of an OrderedDict from the `collections` module.
# - OrderedDict provides fast O(1) access, insertion, and deletion while maintaining the order of elements.
# - When a key is accessed, it is moved to the end to mark it as recently used.
# - If the cache exceeds capacity during `put`, the least recently used item (the first element) is removed.

# Time Complexity : O(1) for both `get` and `put`.
# Space Complexity : O(capacity), as we store up to `capacity` items.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None


class LRUCache:
    def __init__(self, capacity: int):
        """
        Initialize the LRUCache with a given capacity.
        """
        self.cache = OrderedDict()  # OrderedDict to maintain the order of insertion
        self.capacity = capacity  # Maximum capacity of the cache

    def get(self, key: int) -> int:
        """
        Retrieve the value of the key if it exists in the cache.
        Move the accessed key to the end to mark it as recently used.
        """
        if key in self.cache:
            self.cache.move_to_end(key)  # Move the key to the end (most recently used)
            return self.cache[key]  # Return the value associated with the key
        return -1  # Return -1 if the key is not in the cache

    def put(self, key: int, value: int) -> None:
        """
        Add a key-value pair to the cache.
        If the key exists, update the value and mark it as recently used.
        If the key does not exist and the cache is at capacity, evict the least recently used key.
        """
        if key in self.cache:
            self.cache.move_to_end(key)  # Mark the key as recently used
        self.cache[key] = value  # Insert or update the key-value pair
        if len(self.cache) > self.capacity:  # If capacity is exceeded
            self.cache.popitem(last=False)  # Remove the least recently used item (first item)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key, value)

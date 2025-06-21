"""
To maintain access order, Use an OrderedDict or a hashmap + doubly linked list
On get/put, move the key to the "most recently used" end
Remove the least recently used item from the front, if capacity exceeded
"""
"""
Time Complexity: O(1) for both get() and put() due to OrderedDict
Space Complexity: O(capacity) for storage
"""


from collections import OrderedDict

class LRUCache:
    def __init__(self, capacity: int):
        self.cache = OrderedDict()
        self.capacity = capacity

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        self.cache.move_to_end(key)  
        return self.cache[key]

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.cache.move_to_end(key)
        self.cache[key] = value
        if len(self.cache) > self.capacity:
            self.cache.popitem(last=False)  


if __name__ == "__main__":
    lru = LRUCache(2)
    lru.put(1, 1)
    lru.put(2, 2)
    print(lru.get(1)) 
    lru.put(3, 3)     
    print(lru.get(2))  
    lru.put(4, 4)      
    print(lru.get(1)) 
    print(lru.get(3)) 
    print(lru.get(4)) 

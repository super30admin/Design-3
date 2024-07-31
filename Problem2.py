# I designed a Least Recently Used (LRU) cache that utilizes a dictionary to store key-value pairs, ensuring efficient access and updates. The cache has a fixed capacity specified at initialization. The get method retrieves the value for a given key, moving the key to the end to mark it as recently used, or returns -1 if the key is not found. The put method inserts or updates a key-value pair, also moving the key to the end to mark it as recently used. If the cache exceeds its capacity, the oldest item (the first item in the dictionary) is removed. The time complexity for both get and put operations is O(1) due to the efficient handling of dictionary operations and key management. The space complexity is O(capacity) because the dictionary only stores up to the specified number of key-value pairs.

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.items = dict[int, int]()
        
    def get(self, key: int) -> int:
        if (value := self.items.pop(key, None)) is None:
            return -1
        self.items[key] = value
        return value        

    def put(self, key: int, value: int) -> None:
        self.items.pop(key, None)
        self.items[key] = value
        if len(self.items) > self.capacity:
            del self.items[next(iter(self.items))]
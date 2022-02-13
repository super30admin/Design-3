class LRUCache:
    """
    1. Python's dictionaries support insertion order by default.
    2. You can grab access to the first key in a dictionary using either next(iter(dict)) or 
    list(dict[0])
    3. TC : O(1)
    4. SC : O(N)
    """
    def __init__(self, capacity: int):
        self.cache = dict()
        self.capacity = capacity
        
    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        
        else:
            value = self.cache[key]
            self.cache.pop(key)
            self.cache[key] = value
            return value
        

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.cache.pop(key)
            
        else:
            if len(self.cache) >= self.capacity:
                self.cache.pop(next(iter(self.cache)))
        
        self.cache[key] = value  

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

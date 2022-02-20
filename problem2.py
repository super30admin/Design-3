#Time Complexity O(1)
#Space Complexity O(N)
class LRUCache:
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

# Time Complexity o(n)
# Space Complexity o(n)

class LRUCache:

    def __init__(self, capacity: int):
        
        
        self.hmap = {}
        self.capacity = capacity
        self.listKeys = []
        

    def get(self, key: int) -> int:
        
        if key not in self.hmap:
            
            return -1
        
        self.listKeys.remove(key)
        self.listKeys.append(key)
        
        return self.hmap[key]
        

    def put(self, key: int, value: int) -> None:
        
        if key in self.hmap:
            
            self.listKeys.remove(key)
        elif len(self.hmap) == self.capacity:
            v =self.listKeys.pop(0)
            
            self.hmap.pop(v)
        print(key)
        self.hmap[key] = value
        self.listKeys.append(key)
class LRUCache:

    def __init__(self, capacity: int):
        self.dict = {}
        self.cap = capacity

    def get(self, key: int) -> int:
        if key not in self.dict : 
            return -1
        k,v = key, self.dict[key]
        del self.dict[key]
        self.dict[k] = v
        return self.dict[key]

    def put(self, key: int, value: int) -> None:
        if key not in self.dict:
            self.dict[key] = value
        else:
            k,v = key, self.dict[key]
            del self.dict[key]
            self.dict[key] = value
        if len(self.dict)>self.cap:
            del self.dict[list(self.dict.keys())[0]]
            
            
# Time Complexity: O(1)
# Space Complexity: O(n) n = capacity
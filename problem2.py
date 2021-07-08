class LRUCache:

    def __init__(self, capacity: int):
        self.stack = [] #Most recent -> least recent structure
        self.lookup = {}
        self.capacity = capacity

    def get(self, key: int) -> int:
        if key not in self.lookup: return -1
        result = self.lookup[key]
        self.stack.remove(result)
        self.stack.insert(0,result)
        return result[1]
    
        

    def put(self, key: int, value: int) -> None:
         if self.capacity > len(self.lookup): 
            if key not in self.lookup:
                self.lookup[key] = (key, value)
                self.stack.insert(0, (key, value))
            else:
                temp = self.lookup[key]
                self.lookup[key] = (key, value)
                self.stack.remove(temp)
                self.stack.insert(0, (key, value))
        
         elif self.capacity == len(self.lookup):
            if key in self.lookup:
                temp = self.lookup[key]
                self.lookup[key] = (key, value)
                self.stack.remove(temp)
                self.stack.insert(0, (key, value))
            else:
                removed = self.stack.pop()
                self.lookup.pop(removed[0])
                self.lookup[key] = (key, value)
                self.stack.insert(0, (key, value))
                
#Time complexity is O(n) as we are iterating over the array for each get and put operation
#Space complexity is O(n) 
                
                

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
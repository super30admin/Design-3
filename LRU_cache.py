# Time Complexity :O(N)
# Space Complexity :O(N)
# Did this code successfully run on Leetcode : Yes

# Any problem you faced while coding this :

# Your code here along with comments explaining your approach


class LRUCache:

    def __init__(self, capacity: int):
        self.dict={}
        self.capacity=capacity
        self.key_list=[]

    def get(self, key: int) -> int:
        if(key not in self.dict):
            return -1
        self.key_list.remove(key)
        self.key_list.append(key)
        return self.dict[key]

    def put(self, key: int, value: int) -> None:
        
        if(key in self.dict):
            self.key_list.remove(key)
        else:
            if(len(self.dict)==self.capacity):
                val=self.key_list.pop(0)
                self.dict.pop(val)
        self.dict[key]=value
        self.key_list.append(key)
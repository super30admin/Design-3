#Time: O(1)
#space: O(capacity)

class node:
        def __init__(self,key,value):
            self.key=key
            self.value=value
            self.prev=None
            self.next=None
class LRUCache:
       
    head=node(-1,-1)
    tail=node(-1,-1)
    def __init__(self, capacity: int):
        self.hashmap={}
        self.capacity=capacity
        self.tail.prev=self.head
        self.head.next=self.tail
    def get(self, key: int) -> int:
        if key in self.hashmap:
            self.remove(self.hashmap[key])
            self.puthead(self.hashmap[key])
            return self.hashmap[key].value
        else:
            return -1
            

    def put(self, key: int, value: int) -> None:
        if key not in self.hashmap:
            if(len(self.hashmap)<self.capacity):
                curr=node(key,value)
                self.puthead(curr)
                self.hashmap[key]=curr
            else:
                curr=node(key,value)
                self.hashmap[key]=curr
                self.puthead(curr)
                self.hashmap.pop(self.tail.prev.key)
                self.remove(self.tail.prev)
        else:
            self.remove(self.hashmap[key])
            self.puthead(self.hashmap[key]) 
            self.hashmap[key].value=value
               
            
    def remove(self,curr):
        curr.next.prev=curr.prev
        curr.prev.next=curr.next
        
        
    def puthead(self,curr):
        
        self.head.next.prev=curr
        curr.next=self.head.next
        curr.prev=self.head
        self.head.next=curr
        
                
        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
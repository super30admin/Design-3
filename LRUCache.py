class Node:
    def __init__(self, key, val, prev):
        self.key = key
        self.val = val
        self.prev = prev
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.max_capacity = capacity
        self.head = Node(None, None, None)
        self.tail = self.head
        self.cache = dict()
        

    def get(self, key: int) -> int:
        if key in self.cache:
            self.arrange(self.cache[key])
            
            return self.cache[key].val
        return -1
        
        

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.cache[key].val = value
            self.arrange(self.cache[key])
        else:
            self.cache[key] = Node(key, value, self.tail)
            self.tail.next = self.cache[key]
            self.tail = self.tail.next
            
            if len(self.cache) > self.max_capacity:
                del self.cache[self.head.next.key]
                self.head.next = self.head.next.next
                self.head.next.prev = self.head
            
        
    
    
    
    def arrange(self, node):
        if node != self.tail:
            node.prev.next = node.next
            node.next.prev = node.prev
            self.tail.next = node
            node.prev = self.tail
            self.tail = self.tail.next
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
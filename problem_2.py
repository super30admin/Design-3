# Time Complexity - O(1)
# Space Complexity - O(capacity)

class Node: 
     def __init__(self, key=None, val= None):
      
            self.next = None
            self.prev = None
            self.key = key 
            self.val = val


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail 
        self.tail.prev = self.head
        
        

    def get(self, key: int) -> int:
        if key in self.map : 
            self.remove(self.map[key]) 
            self.add(self.map[key]) 
            return self.map[key].val 
        return -1 
        
        

    def put(self, key: int, value: int) -> None:
        if key in self.map : 
            self.remove(self.map[key]) 
        node = Node(key,value)
        self.add(node) 
        self.map[key] = node 
        if len(self.map) > self.capacity: 
            node = self.head.next 
            self.remove(node) 
            del self.map[node.key]

    def remove(self,node):
        node.prev.next = node.next 
        node.next.prev = node.prev 

    def add(self,node):
        node.next = self.tail
        node.prev = node.next.prev
        node.prev.next = node
        node.next.prev = node
       

    

        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
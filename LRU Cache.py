class DLLNode:
    def __init__(self,key,val):
        self.val = val
        self.key = key
        self.prev = None
        self.next = None
        
class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = collections.defaultdict()
        self.head = DLLNode(-1,-1)
        self.tail = DLLNode(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
             
    def addNode(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None
    
    def removelastNode(self):
        node = self.tail.prev
        self.removeNode(node)
        
    def get(self, key: int) -> int: #O(1)
        if key not in self.hashmap:
            return -1
        self.removeNode(self.hashmap[key])
        self.addNode(self.hashmap[key])
        return self.hashmap[key].val
        
    def put(self, key: int, value: int) -> None:  #O(1)
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node) 
        else:
            node = DLLNode(key,value)
            if len(self.hashmap)>= self.capacity:
                lastNodekey = self.tail.prev.key
                self.removelastNode()
                del self.hashmap[lastNodekey]
            self.hashmap[key] = node
        self.addNode(node)
            
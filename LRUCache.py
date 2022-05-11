class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.hashMap = {}
        self.cap = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail 
        self.tail.prev = self.head
        
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev 
        
    def addToHead(self,node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node  

    def get(self, key: int) -> int:
        if key not in self.hashMap:
            return -1
        
        node = self.hashMap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.val
        
    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            node = self.hashMap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
            return
        
        if self.cap == len(self.hashMap):
            nodePrev = self.tail.prev
            self.removeNode(nodePrev)
            del self.hashMap[nodePrev.key]
            
        node = Node(key,value)
        self.hashMap[key] = node
        self.addToHead(node)
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
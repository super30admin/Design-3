class Node:
    def __init__(self, key: int,val : int):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def removeNode(self,node):
        node.next.prev = node.prev
        node.prev.next = node.next
    
    def addtoHead(self,node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node 

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addtoHead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addtoHead(node)
        else:
            if len(self.map) == self.capacity:
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                self.map.pop(tailPrev.key)
            
            newNode = Node(key,value)
            self.addtoHead(newNode)
            self.map[key] = newNode
    

    
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
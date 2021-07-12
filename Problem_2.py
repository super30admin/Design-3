class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None
        
class LRUCache:
    def addNodetoHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
    
    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
        
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head  
        
    def get(self, key: int) -> int:
        if key not in self.map.keys(): 
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addNodetoHead(node)
        return node.value
        
    def put(self, key: int, value: int) -> None:
        if key in self.map.keys():
            node = self.map[key]
            node.value = value
            self.removeNode(node)
            self.addNodetoHead(node)
            return node.value
        else:
            if self.capacity == len(self.map):
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                self.map.pop(tailPrev.key)
            newNode = Node(key, value)
            self.addNodetoHead(newNode)
            self.map[key] = newNode
            
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

# Time Complexity: O(1) for put and get operations
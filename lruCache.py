#Time complexity: O(1)
#space complexity: O(1)
class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
        self.map = dict()
        
    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def addToHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
        
        
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
    
        self.removeNode(self.map[key])
        self.addToHead(self.map[key])
        return self.map[key].val
        
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self.map[key].val = value
            self.removeNode(self.map[key])
            self.addToHead(self.map[key])
        else:
            if len(self.map) == self.capacity:
                tailPrev = self.tail.prev
                del self.map[tailPrev.key]
                self.removeNode(tailPrev)
            newNode = Node(key, value)
            self.addToHead(newNode)
            self.map[key] = newNode

            
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

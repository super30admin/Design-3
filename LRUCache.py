class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hMap = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def addToHead(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def get(self, key: int) -> int:
        if key not in self.hMap: return -1
        node = self.hMap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.hMap:
            node = self.hMap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if self.capacity == len(self.hMap):
                #remove LRU
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                self.hMap.pop(tailPrev.key)
            newNode = Node(key,value)
            self.hMap[key] = newNode
            self.addToHead(newNode)
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)